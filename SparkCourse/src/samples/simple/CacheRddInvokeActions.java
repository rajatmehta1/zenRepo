package samples.simple;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;

public class CacheRddInvokeActions {
	private static String appName = "Simple Cache RDD App";
	private static String master = "local";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rowRdd = sc.textFile("univ_rankings.csv");
		
			rowRdd.persist(StorageLevel.MEMORY_ONLY());// this would only store in memory	
			
			System.out.println("Total no. of rows --->" + rowRdd.count());
			
			System.out.println("RDD is cached");
			
			System.out.println("First Row --->" + rowRdd.first());
	}

}
