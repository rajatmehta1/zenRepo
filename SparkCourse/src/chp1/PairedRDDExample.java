package chp1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.supercsv.cellprocessor.ParseInt;

import scala.Tuple2;

public class PairedRDDExample {

	private static String appName = "PairedRddExample";
	private static String master = "local[*]";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rddX = sc.parallelize(
                Arrays.asList("videoName1,5","videoName2,6", "videoName3,2","videoName1,6"));

		JavaPairRDD<String, Integer> videoCountPairRdd = rddX.mapToPair((String s) -> {
			String[] arr = s.split(",");
			return new Tuple2<String, Integer>(arr[0], Integer.parseInt(arr[1]));
		});
	    
		List<Tuple2<String,Integer>> testResults = videoCountPairRdd.collect();
		for (Tuple2<String, Integer> tuple2 : testResults) {
			System.out.println(tuple2._1);
		}
	}

}
