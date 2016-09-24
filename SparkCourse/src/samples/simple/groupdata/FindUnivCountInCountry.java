package samples.simple.groupdata;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

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
 * Use Case : Group the universities by country and find number of univ in each country
 */
public class FindUnivCountInCountry {
	private static String appName = "Count Universities in each Country";
	private static String master = "local";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rowRdd = sc.textFile("univ_rankings.csv");
		JavaPairRDD<String, String> countryUnivPairRdd = rowRdd.mapToPair(new PairFunction<String, String, String>() {

			@Override
			public Tuple2<String, String> call(String rowStr) throws Exception {
				String[] strArr = rowStr.split(",");
				return new Tuple2<String, String>(strArr[1], strArr[0]); // note this tuple is like an object of key,value pair where _1 is key and _2 is value
			}
		});
		
		List<Tuple2<String,String>> rs = countryUnivPairRdd.collect();
		for (Tuple2<String, String> tuple2 : rs) {
			System.out.println(tuple2._1);
		}
//		JavaPairRDD<String, Iterable<String>> grpByCntryRdd =  rowRdd.gr
		
	}
}
