package chp1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;

public class ActionExamples {

	private static String appName = "LOAD_DATA_APPNAME";
	private static String master = "local";
	private static String FILE_NAME = "univ_rankings.txt";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rddX = sc.parallelize(
                Arrays.asList("big data","analytics", "using java"));
		List<String> strs = rddX.collect();
		for (String str : strs) {
			System.out.println(str);
		}
		
		//Aggregation
		JavaRDD<String> rddX2 = sc.parallelize(
                Arrays.asList("1","2", "3"));
	    String sumResult = rddX2.reduce((String x, String y) -> {
	        	return "" + (Integer.parseInt(x) + Integer.parseInt(y));
	        });
	    System.out.println("sumResult ==>"+sumResult);
	    
	   //store to file
	    JavaRDD<String> rddX3 = sc.parallelize(
                Arrays.asList("element-1","element-2", "element-3","element-4","element-5"));
	    rddX3.foreach(f -> System.out.println(f));
	  //  rddX3.coalesce(1).saveAsTextFile("file:///C:/harpreet/temp/output11");
	    
	}

}
