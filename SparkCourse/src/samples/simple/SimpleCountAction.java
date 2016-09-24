package samples.simple;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
/*
 * In this example we analyse a dataset of university rankings. 
 * The accuracy of this data is not important at this time, we just want to learn to take some action on it.
 * 
 * This dataset has on each row (comma separated values) :
 * 1 First value is the Name of university
 * 2 Second value is the country its in
 * 
 * Note : The rankings are already sorted in this dataset
 * 
 * 
 * 
 * 
 *  Find the number of colleges in the dataset - 
 *  Note : in single row we have one college so count of rows is also equal to the 
 *  number of colleges.
 */
public class SimpleCountAction {
	private static String appName = "Count Action App";
	private static String master = "local";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rowRdd = sc.textFile("univ_rankings.csv");
		
			System.out.println("Total no. of rows --->" + rowRdd.count());
	}

}
