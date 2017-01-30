package chp3;

import java.awt.Color;
import java.util.List;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/*
 *
 *
 * TimeSeries Plot of Jan to Dec temp in 2015 vs Jan to Dec in 2014
 *
 */
public class TimeSeriesChartExample extends ApplicationFrame {
	public static String APP_NAME = "TIME_SERIES_EXAMPLE";
	public static String APP_MASTER = "local";

	public TimeSeriesChartExample(final String title) {
		super(title);
		final XYDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		chart.getPlot().setBackgroundPaint(Color.WHITE);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
		chartPanel.setMouseZoomable(true, false);
		// chartPanel.setB
		setContentPane(chartPanel);
	}

	// private XYDataset createDataset( )
	// {
	// final TimeSeries series = new TimeSeries( "Random Data" ); // Second
	// current = new Second( ); // double value = 100.0; // for (int i = 0; i <
	// 4000; i++)
	// {
	// try
	// {
	// value = value + Math. random ( ) - 0.5; // series.add(current, new Double
	// ( value) ); // current = ( Second) current.next( );
	// }
	// catch ( SeriesException e
	// {
	// System.err.println("Error adding to series");
	// }
	// }
	//
	// return new TimeSeriesCollection(series);
	// }

	private XYDataset createDataset() {
		final TimeSeries series = new TimeSeries("Jan-Dec 2015");
		SparkConf sconf = new SparkConf().setAppName(APP_NAME).setMaster(APP_MASTER);
		JavaSparkContext se = new JavaSparkContext(sconf);
		SQLContext sqlContext = new SQLContext(se);
		Dataset<Row> df = sqlContext.read().format("json").json("data/india_temp.json");
		df.createOrReplaceTempView("india_temp");
		Dataset<Row> dfc = sqlContext.sql("select explode(data) from india_temp");
		JavaRDD<Row> rdd = dfc.javaRDD();
		JavaRDD<Row> filterRdd = rdd.filter(new Function<Row, Boolean>() {
			@Override
			public Boolean call(Row row) throws Exception {
				// List<List<String>> rowList1 = row.getList(0);
				List<String> rowList = row.getList(0);
				if ("2015".equals(rowList.get(0).toString()))
					return true;
				return false;
			}
		});

		List<Row> filterList = filterRdd.collect();
		for (Row row : filterList) {
			List<String> items = row.getList(0);
			for (int i = 1; i <= 12; i++) {
				series.add(new Month(i, Integer.parseInt(items.get(0))), new Double(items.get(i)));
			}
		}
		return new TimeSeriesCollection(series);
	}

	private JFreeChart createChart(final XYDataset dataset) {
		return ChartFactory.createTimeSeriesChart("TimeSeries -Temperatures vs Months (2015) ", "Months (2015)",
				"Avg. Temperature", dataset, false, false, false);
	}

	public static void main(final String[] args) {
		final String title = "Time Series Management";
		final TimeSeriesChartExample demo = new TimeSeriesChartExample(title);
		demo.pack();
		RefineryUtilities.positionFrameRandomly(demo);
		demo.setVisible(true);
	}

}