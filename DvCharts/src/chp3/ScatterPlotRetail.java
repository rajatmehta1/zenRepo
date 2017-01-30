package chp3;

public class ScatterPlotRetail {

	private static final long serialVersionUID = lL;
	XYDataset inputData;
	JFreeChart chart;
	public static String APP_NAME = "SCATTER_PLOT_EXAl'VlPLE";
	public static String APP MASTER = "local";
	public ScatterPlotRetail(String title) {
	super(title);
	inputData = createDataSet(
	"C:/FAST/eclipse/workspaces/Spark/SparkCharts/src/resources/online_retail.csv");
	chart = createChart(inputData);
	ChartPanel cPanel = new ChartPanel(chart);
	cPanel.setPreferredSize(new java.awt.Dimension(500,270»;
	setContentPane(cPanel);
	}
	public static void main(String[] args) {
	ScatterPlotRetail demo = new ScatterPlotRetail("Scatter Plot");
	demo.pack();
	RefineryUtilities.centerFrameOnScreen(demo);
	demo.setVisible(true);
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
			arr[O] = Double.parseDouble(strs[3]);
			arr[l] = Double.parseDouble(strs[5]);
			return arr;
			}
			}) ;
			List<Double[]> dataltems = dataRowArr.collect();
			XYSeriesCollection dataset = new XYSeriesCollection() ;
			XYSeries series = new XYSeries("Retail Scatter Plot");
			for (Double[] darr : dataltems) {
			Double quantity = darr[O];
			Double price = darr[l];
			series.add(quantity, price);
			}			
		
			dataset.addSeries(series);
			return dataset;
			}
			private JFreeChart createChart(XYDataset inputDataSet) {
			JFreeChart chart = ChartFactory.createScatterPlot("Retail Scatter Plot", "Quantity",
			"Price", inputDataSet, PlotOrientation.VERTICAL, true, true, false);
			XYPlot plot = chart.getXYPlot();
			plot.getRenderer() .setSeriesPaint(O, Color.green);
			return chart;
			}
			}
		

}
