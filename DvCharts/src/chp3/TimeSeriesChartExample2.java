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
import org.apache.spark.sql.SparkSession;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.general.SeriesException; 
import org.jfree.data.time.Month; 
import org.jfree.data.time.Second; 
import org.jfree.data.time.TimeSeries; 
import org.jfree.data.time.TimeSeriesCollection; 
import org.jfree.data.xy.DefaultXYDataset; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

/* 
 * TimeSeries plot of Jan to Dec temp in 2015 vs Jan to Dec in 2014 
 * 
 */ 
public class TimeSeriesChartExample2 extends ApplicationFrame { 
	public static String APP_NAME = "TimeSeriesChartExample2"; 
	public static String APP_MASTER = "local"; 
	
	public TimeSeriesChartExample2(final String title) 
	{ 
		super(title); 
		final XYDataset dataset = createDataset(); 
		final JFreeChart chart = createChart(dataset); 
			chart.getPlot().setBackgroundPaint(Color.WHITE);
		final ChartPanel chartPanel = new ChartPanel(chart); 
				chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) ); 
				chartPanel.setMouseZoomable( true, false ); 
				setContentPane(chartPanel); 
	} 
				
	private XYDataset createDataset() 
	{ 
		DefaultXYDataset ds = new DefaultXYDataset(); 
		SparkConf sconf = new SparkConf().setAppName(APP_NAME).setMaster(APP_MASTER);  
		SparkSession spark = SparkSession.builder()
				  				.config(sconf)
				  				.getOrCreate();
		Dataset<Row> df = spark.read().format("json").json( "data/india_temp.json"); 
					 df.createOrReplaceTempView("india_temp"); 
		Dataset<Row> dfc = spark.sql("select explode(data) from india_temp"); 
		JavaRDD<Row> rdd = dfc.javaRDD(); 
		JavaRDD<Row> filterRdd = rdd.filter(s -> {
			if("2015".equals(s.getList(0).get(0).toString())) return true;
			else if("2014".equals(s.getList(0).get(0).toString())) return true;
			else return false;
		});
		
		List<Row> filterList = filterRdd.collect();
		int j = 0;
		for (Row row : filterList) {
			double[][] series = new double[2][13]; 
			List<String> items = row.getList(0); 
			for(int i = 1 ; i <= 12 ; i++) { 
				series[0][i] = (double)i; 
				series[1][i] = new Double(items.get(i)) ; 
			} 
				ds.addSeries("Series-" + j, series); 
				j = j + 1; 
		}
		return ds; 
	}
	
	private JFreeChart createChart(final XYDataset dataset) 
	{ 
		return ChartFactory.createTimeSeriesChart( "Year vs Avg Temp", 
													"Year" , 
													"Avg Temp", 
													dataset, 
													false, 
													false, 
													false); 
	}
	
	public static void main(final String[ ] args) 
	{ 
		final String title = "Time Series Management"; 
		final TimeSeriesChartExample2 demo = new TimeSeriesChartExample2(title); 
			demo.pack(); 
		RefineryUtilities.positionFrameRandomly(demo); 
		demo.setVisible(true); 
	}
		
} 
