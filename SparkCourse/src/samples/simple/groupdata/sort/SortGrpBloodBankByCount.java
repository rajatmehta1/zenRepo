package samples.simple.groupdata.sort;

import java.util.Comparator;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import samples.simple.filterdata.filter.BloodBankByStateFilter;
import scala.Function;
import scala.Tuple2;

public class SortGrpBloodBankByCount {

	private static String appName = "Sort States with Blocd Bank by Count";
	private static String master = "local";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rowRdd = sc.textFile("bloodbank_india.csv");

		//Writing Map to Pair in a Separate Class
		JavaPairRDD<String, Integer> stateBbCntRdd = rowRdd.mapToPair(new StateToBloodBankMapper());
		
		
		

		
		JavaPairRDD<String, Integer> stateBbCntGrpRdd = stateBbCntRdd.reduceByKey(new Function2<Integer, Integer, Integer>() {
			
			@Override
			public Integer call(Integer val1, Integer val2) throws Exception {
				// TODO Auto-generated method stub
				return val1 + val2;
			}
		});
		List<Tuple2<String, Integer>> rows = stateBbCntGrpRdd.collect();
		for (Tuple2 row : rows) {
			System.out.println(" -----------------> " + row._2);
		}
		JavaPairRDD<String, Integer> rddSortedByKey = stateBbCntGrpRdd.sortByKey();
		
		List<Tuple2<String,Integer>> sortedByKeyList = rddSortedByKey.collect();
		for (Tuple2<String, Integer> tuple2 : sortedByKeyList) {
			System.out.println(tuple2.toString());
		}
		//System.out.println("Number of blood banks in delhi : " + rows.size());
		
		//Sort by value, reverse keys
		JavaPairRDD<Integer, String> stateBbCntGrpRddReversed =  stateBbCntGrpRdd.mapToPair(new PairFunction<Tuple2<String,Integer>, Integer, String>() {

			@Override
			public Tuple2<Integer, String> call(Tuple2<String, Integer> t) throws Exception {
				return new Tuple2(t._2,t._1);
			}
		});
		
		JavaPairRDD<Integer, String> rddSortedByKey1 = stateBbCntGrpRddReversed.sortByKey();
		
		List<Tuple2<Integer,String>> sortedByKeyList1 = rddSortedByKey1.collect();
		for (Tuple2<Integer, String> tuple21 : sortedByKeyList1) {
			System.out.println(tuple21.toString());
		}
	}


}
