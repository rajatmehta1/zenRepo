package samples.complex.ds.clustering;

import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

/**
 * Example using MLlib KMeans from Java.
 */
public final class JavaKMeans {
	private static String appName = "KMeans Example 1";
	private static String master = "local";
	
  private static class ParsePoint implements Function<String, Vector> {
    private static final Pattern SPACE = Pattern.compile(" ");
	
    @Override
    public Vector call(String line) {
      String[] tok = SPACE.split(line);
      double[] point = new double[tok.length];
      for (int i = 0; i < tok.length; ++i) {
        point[i] = Double.parseDouble(tok[i]);
      }
      return Vectors.dense(point);
    }
  }

  public static void main(String[] args) {
    //String inputFile = args[0];
    int k = 3;
    int iterations = 11;
    int runs = 5;

	SparkConf conf = new SparkConf().setAppName(appName).setMaster(master);
	JavaSparkContext sc = new JavaSparkContext(conf);
	JavaRDD<String> linesrdd = sc.textFile("kmeans_data.txt");

    JavaRDD<Vector> points = linesrdd.map(new ParsePoint());

    KMeansModel model = KMeans.train(points.rdd(), k, iterations, runs, KMeans.K_MEANS_PARALLEL());

    System.out.println("Cluster centers:");
    for (Vector center : model.clusterCenters()) {
      System.out.println(" " + center);
    }
    double cost = model.computeCost(points.rdd());
    System.out.println("Cost: " + cost);

    sc.stop();
  }
}

