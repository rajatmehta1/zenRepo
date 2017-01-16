package chp1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;

public class FlatMapExample {

	private static String appName = "LOAD_DATA_APPNAME";
	private static String master = "local";
	private static String FILE_NAME = "univ_rankings.txt";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rddX = sc.parallelize(
                Arrays.asList("big data","analytics", "using java"));
		 JavaRDD<String[]> rddY = rddX.map(e -> e.split(" "));
         JavaRDD<String> rddY2 = rddX.flatMap(e -> Arrays.asList(e.split(" ")).iterator());
	        List<String> listUsingFlatMap = rddY2.collect();
	        
		
	}

}
