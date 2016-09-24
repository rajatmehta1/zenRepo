package samples.simple.filterdata;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.functions;

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
 * Use Case : Filter and find only the universities that belong in USA
 */
public class TopUSUniversities {

	private static String appName = "Count Universities in each Country";
	private static String master = "local";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rowRdd = sc.textFile("univ_rankings.csv");
		JavaRDD<String> usaRowsOnlyRdd = rowRdd.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String rowString) throws Exception {
				// Due to the code below we only return the values that are in United States of America
				return rowString.contains("United States of America");
			}
		});
		
		// Note: the filtered string contain both the country and university name, now just extract the university names
		JavaRDD<String> usUnivNamesRDD = usaRowsOnlyRdd.map(new Function<String, String>() {

			@Override
			public String call(String usaRowStr) throws Exception {
				String[] rowArr = usaRowStr.split(","); // values are comma separated e.g California Institute of Technology,United States of America
				return rowArr[0];
			}
		});
		
		List<String> univNamesList = usUnivNamesRDD.collect();
		for (String univName : univNamesList) {
			System.out.println("Name : " + univName);
		}
		
	}
}
