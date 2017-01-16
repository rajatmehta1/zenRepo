package chp1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;

public class LoadData {

	private static String appName = "LOAD_DATA_APPNAME";
	private static String master = "local";
	private static String FILE_NAME = "univ_rankings.txt";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rowRdd = sc.textFile(FILE_NAME);
			System.out.println(rowRdd.count());
		JavaRDD arr = rowRdd.flatMap(x -> Arrays.asList(x.split(",")).iterator());
		List<String> l = arr.take(3);
			for (String object : l) {
				System.out.println(object);
				System.out.println("--------------------");
			}
		//JavaRDD<String> filteredRows = rowRdd.filter(s -> s.contains("Santa Barbara"));
		//	System.out.println(filteredRows.count());
//		JavaRDD<Integer> rowlengths = rowRdd.map(s -> s.length());
//		List<Integer> rows = rowlengths.collect();
//		for (Integer row : rows) {
//			System.out.println(row);
//		}


	}

}
