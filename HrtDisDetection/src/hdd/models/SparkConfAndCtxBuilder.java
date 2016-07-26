package hdd.models;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class SparkConfAndCtxBuilder {
   private JavaSparkContext sCtx;
   
   public SparkConfAndCtxBuilder() {
	   
   }
   
   public JavaSparkContext loadSimpleSparkContext(String appName, String appType) {
	   SparkConf conf = new SparkConf().setAppName(appName).setMaster(appType);
	   sCtx = new JavaSparkContext(conf);
	   	return sCtx;
   }
   
   public void closeCtx() {
	   sCtx.close();
   }
}
