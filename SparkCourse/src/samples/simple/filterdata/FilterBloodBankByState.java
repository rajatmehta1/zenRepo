package samples.simple.filterdata;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import samples.simple.filterdata.filter.BloodBankByStateFilter;

/*
 * This class used blood bank db from india (data.gov.in)
 * This shows the blood banks in India
 * 
 * Use Case : Filter the blood banks in Delhi, and use the Filter in a separate class
 */
public class FilterBloodBankByState {
	private static String appName = "Filter_In_Separate_Class";
	private static String master = "local";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rowRdd = sc.textFile("bloodbank_india.csv");

		
		JavaRDD<String> rowsFilterByStateRDD = rowRdd.filter(new BloodBankByStateFilter());
		
		List<String> rows = rowsFilterByStateRDD.collect();
		for (String row : rows) {
			System.out.println(" -----------------> " + row);
		}
		
		System.out.println("Number of blood banks in delhi : " + rows.size());
	}

}
