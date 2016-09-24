package samples.simple;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.JavaRDD;

import java.util.List;

import org.apache.spark.SparkConf;

/*
 * Simple Program that 
 *  1) Creates and Initiates the spark context
 *  2) Reads a sample file
 *  3) Print out the first line of the file which is 'Hello World'
 */
public class ReadDataAndPrint {

	private static String appName = "Hello World App";
	private static String master = "local";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rowRdd = sc.textFile("hello.txt");
		JavaRDD<String> filteredRows = rowRdd.filter(new Function<String, Boolean>() {
			
			@Override
			public Boolean call(String singleLine) throws Exception {
				// TODO Auto-generated method stub
				return singleLine.contains("error");
			}
		});
//		List<String> rows = rowRdd.collect();
//		
//		for (String row : rows) {
//			System.out.println("row is -->" + row);
//		}
	}

	
}
