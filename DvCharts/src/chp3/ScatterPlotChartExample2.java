package chp3;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public class ScatterPlotChartExample2 extends ApplicationFrame {
	XYDataset inputData;
	JFreeChart chart;
	public static String APP_NAME = "SCATTER_PLOT_EXAMPLE";
	public static String APP_MASTER = "local";
	
	public ScatterPlotChartExample2(String title) {
		super(title);
		inputData = createDataSet("data/housingprices.txt");
			chart = createChart(inputData);
			chart.getPlot().setBackgroundPaint(Color.WHITE);
		ChartPanel cPanel = new ChartPanel(chart);
			cPanel.setPreferredSize(new java.awt.Dimension(500,270));
				setContentPane(cPanel);
	}
	
	public static void main(String[] args) {
		ScatterPlotChartExample2 demo = new ScatterPlotChartExample2 ("Scatter Plot");
			demo.pack ();
		RefineryUtilities.centerFrameOnScreen(demo);
			demo.setVisible(true);
	}
	
	
	private JFreeChart createChart(XYDataset inputDataSet) {
		JFreeChart chart = ChartFactory.createScatterPlot("price for Living Areal", "LivingArea", "Price", inputDataSet, PlotOrientation.VERTICAL, true, true, false);
		XYPlot plot = chart.getXYPlot();
		plot.getRenderer().setSeriesPaint(0, Color.green);
		return chart;
	}

	
	private XYDataset createDataSet(String datasetFileName) {
		SparkConf sconf = new SparkConf() .setAppName(APP_NAME) .setMaster(APP_MASTER);
		JavaSparkContext sc = new JavaSparkContext(sconf);
		SQLContext sqlContext = new SQLContext(sc);
		JavaRDD<String> dataRows = sc.textFile(datasetFileName);
		JavaRDD<Double[]> dataRowArr = dataRows.map(new Function<String, Double[]>() {
			@Override
			public Double[] call(String line) throws Exception {
				String[] strs = line.split(",");
				Double[] arr = new Double[2];
				arr[0] = Double.parseDouble(strs[5]);
				arr[1] = Double.parseDouble(strs[2]);
				return arr;
			}
		}) ;
		
		List<Double[]> dataltems = dataRowArr.collect();
		XYSeriesCollection dataset =new XYSeriesCollection() ;
		XYSeries series= new XYSeries("real estate item");
		for (Double[] darr : dataltems) {
			Double livingArea = darr[0];
			Double price = darr[1];
			series.add(livingArea, price);
		}
			dataset.addSeries(series);
			return dataset;
	}

	
	// Here we consider the lower percent as 25% and highest as 75%
	public static double quartile(double[] values, double lowerPercent) {
		if (values == null || values.length == 0) {
				throw new IllegalArgumentException("The data array either is null or does not contain any data. ");
		}
		// Rank order the values
	
		double[] v = new double[values.length];
			System.arraycopy(values, 0, v, 0, values.length);
			Arrays.sort(v);
			int n = (int) Math. round (v.length * lowerPercent / 100);
			return v[n];
	}
	
	
}