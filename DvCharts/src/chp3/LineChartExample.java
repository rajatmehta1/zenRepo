package chp3;

import org.jfree.chart.ChartPanel;

import java.awt.Color;
import java.util.List;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import scala.collection.mutable.WrappedArray;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartExample extends ApplicationFrame {


	public static String APP_NAME = "LINE_CHART_EXAMPLE";
	public static String APP_MASTER = "local";
	
	public LineChartExample(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		JFreeChart lineChart = 
				ChartFactory.createLineChart(chartTitle, "Year","Max Temperatue", createDataset(), PlotOrientation.VERTICAL, true, true, false);
		lineChart.getPlot().setBackgroundPaint(Color.WHITE);
		ChartPanel chartPanel = new ChartPanel(lineChart);
				   chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
			setContentPane(chartPanel);
	}
	
	/**
	 * Max Temp and Year in India
	 *
	 */
	private DefaultCategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		SparkConf sconf = new SparkConf().setAppName(APP_NAME).setMaster(APP_MASTER);
		JavaSparkContext sc = new JavaSparkContext(sconf);
		SparkSession sqlContext = SparkSession.builder()
  									.config(sconf)
  									.getOrCreate();
		Dataset<Row> df = sqlContext.read().format("json").json("data/india_temp.json");
			df.createOrReplaceTempView("india_temp");
		Dataset<Row>  dfc = sqlContext.sql("select explode(data) from india_temp");
		List<Row> rows = dfc.collectAsList();
			for (Row row : rows) {
				List<String> dataList = row.getList(0);
				dataset.addValue(new Double (dataList.get(12).toString()), "Max Temp", dataList.get(0).toString());
			}
		return dataset;
	}
	
	public static void main(String[] args) {
		LineChartExample chart = new LineChartExample("Max Temp Vs Year", "Max Temp vs Year") ;
			chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
			chart.setVisible(true);
	}
					
	

}
