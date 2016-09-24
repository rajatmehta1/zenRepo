package samples.simple.groupdata;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class EfficientFindUnivCountByCountry {
	private static String appName = "Count Universities in each Country";
	private static String master = "local";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rowRdd = sc.textFile("univ_rankings.csv");
		
		//The method below creates a paired RDD
		// Paired RDD is nothing but a key, value paired RDD.
		JavaPairRDD<String, Integer> countryUnivPairRdd = rowRdd.mapToPair(new PairFunction<String, String, Integer>() {

			@Override
			public Tuple2<String, Integer> call(String rowStr) throws Exception {
				String[] strArr = rowStr.split(",");
				return new Tuple2<String, Integer>(strArr[1], 1); // note this tuple is like an object of key,value pair where _1 is key and _2 is value
			}
		});
		
		List<Tuple2<String,Integer>> vals = countryUnivPairRdd.collect();
		for (Tuple2<String, Integer> tuple2 : vals) {
			System.out.println(tuple2._1 + "," + tuple2._2);
		}
		JavaPairRDD<String, Integer> cntryGrpUnivCnt = countryUnivPairRdd.reduceByKey(new Function2<Integer, Integer, Integer>() {

			@Override
			public Integer call(Integer val1, Integer val2) throws Exception {
				// TODO Auto-generated method stub
				return val1 + val2;
			}
		});

		List<Tuple2<String,Integer>> grpCntLst = cntryGrpUnivCnt.collect();
		for (Tuple2<String, Integer> tuple2 : grpCntLst) {
			System.out.println(tuple2._1 + "," + tuple2._2);
		}
		
	}

}
