package samples.complex.ds.clustering;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;

import samples.complex.ds.clustering.mappers.KmeansIrisDataSetMapper;
import samples.complex.ds.clustering.mappers.KmeansIrisDataSetMapperFull;

/*
 * In this dataset we evaluate the standard iris-dataset (without the labels)
 * 
 * Its very important to note we will do clustering without labels as then we will
 * try to form groups of flowers based on their attributes. 
 * Using labels we will then try to figure out if they were grouped correctly
 */
public class KMeansClusteringMain {
	private static String appName = "KMeans Clustering - Iris Dataset";
	private static String master = "local";
	
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> linesrdd = sc.textFile("kmeans_data.txt");
		JavaRDD<String[]> fullDataPoints = linesrdd.map(new KmeansIrisDataSetMapperFull());
	    JavaRDD<Vector> datapoints = linesrdd.map(new KmeansIrisDataSetMapper());

	    int k = 3; // Number of clusters we find to find in the data...idealy this should be at the tip of the elbow curve
	    int iterations = 25; // as kmeans is an iterative algorithm we must check how many iterations are needed to get a good fit of data
	    //training the kmeans model
	    KMeansModel model = KMeans.train(datapoints.rdd(), k, iterations);

	    System.out.println("Cluster centers:");
		    for (Vector center : model.clusterCenters()) {
		      System.out.println(" " + center);
		    }
	    double cost = model.computeCost(datapoints.rdd());
	    	System.out.println("Cost: " + cost);

	    List<String[]> fullDataList = fullDataPoints.collect();
	    List<Vector> dataList = datapoints.collect();
	    int dataCnt = dataList.size();
	    for(int i = 0 ; i < dataCnt ; i++) {
	    	Vector dataVect = dataList.get(i);
	    	String flowerSpecieName = (fullDataList.get(i))[4];
	    	System.out.println("Predicted Cluster Value : " + model.predict(dataVect));
	    	System.out.println("Original Flower Grp Name : " + flowerSpecieName);
	    	System.out.println("--------------------------------------------------------");
	    }
	    
	    sc.stop();


	}

}
