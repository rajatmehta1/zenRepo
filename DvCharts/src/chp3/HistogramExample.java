package chp3;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.io.*;
import java.util.List;
import java.util.Random;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import chp3.vo.DataItem;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;

public class HistogramExample extends ApplicationFrame {
	public static String APP_NAME = "HISTOGRAM_CHART_EXAMPLE";
	public static String APP_MASTER = "local";
	
	public HistogramExample(final String title) {
		super(title);
		final HistogramDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(500, 270));
			setContentPane(chartPanel);
	}
	
	
	private HistogramDataset createDataset() {
		SparkConf sconf = new SparkConf() .setAppName(APP_NAME) .setMaster(APP_MASTER);
		JavaSparkContext sc = new JavaSparkContext(sconf);
		SQLContext sqlContext = new SQLContext(sc);
		Dataset<Row> df = sqlContext.read().format("json") .json("data/cars.json");
			df.createOrReplaceTempView("cars");
		Dataset<Row> dfc = sqlContext.sql("select make_country,count(*) from cars group by make_country");
		JavaRDD<DataItem> dataItems = dfc.javaRDD().map(s -> new DataItem(s.getString(0), new Double(s.getLong(1)).doubleValue())) ;
		List<DataItem> dataItemsClt = dataItems.collect();
		double[] values = new double[dataItemsClt.size()];
		for(int i = 0; i < values.length ; i++) {
			values[i] = dataItemsClt.get(i).getValue();
		}
		int binSize = values.length / 5;
		HistogramDataset dataset = new HistogramDataset();
			dataset.setType(HistogramType.FREQUENCY);
			dataset.addSeries("Histogram",values,binSize);
		return dataset;
		}
		private JFreeChart createChart(HistogramDataset dataset) {
		String plotTitle = "Histogram";
		String xaxis = "Number of cars";
		String yaxis = "Frequency";
		PlotOrientation orientation = PlotOrientation.VERTICAL;
		boolean show = false;
		boolean toolTips = false;
		boolean urIs = false;
		JFreeChart chart = ChartFactory.createHistogram( plotTitle, xaxis, yaxis,
		dataset, orientation, show, toolTips, urIs);
		int width = 500;
		int height = 300;
		return chart;
		}
		public static void main(final String[] args) {
		final HistogramExample demo = new HistogramExample("Histogram Demo");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
		}

		
}
