package hdd.models;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.linalg.Vector;
import hdd.models.mappers.TestDataToFeatureVectorMapper;
import org.apache.spark.mllib.classification.NaiveBayesModel;

/*
 * Usage : TestModelHeartDisPredict <testDataLoc> <path-to-model>
 */
public class TestModelHeartDisPredict {
	/*
	 * Testing the model to predict on test data
	 * 
	 */
	public static void main(String[] args) {
		if(args.length < 2) throw new RuntimeException(" Please Use : TestModelHeartDisPredict <testDataLoc> <path-to-model>");
		
		String testDataLoc = args[0];
		String modelStorageLoc = args[1];
		
		System.out.println("Building the spark context and conf");
		SparkConfAndCtxBuilder ctxBuilder = new SparkConfAndCtxBuilder();
		JavaSparkContext jctx = ctxBuilder.loadSimpleSparkContext("Heart Disease Detection App", "local");
		
		JavaRDD<String> dsLines = jctx.textFile(testDataLoc);
		JavaRDD<Vector> fRdd = dsLines.map(new TestDataToFeatureVectorMapper());

		NaiveBayesModel _model = NaiveBayesModel.load(jctx.sc(), modelStorageLoc);
		
		JavaRDD<Double> predictedResults = _model.predict(fRdd);
		List<Double> prl = predictedResults.collect();
		for (Double pr : prl) {
			System.out.println("Predicted Value : " + pr);
		}
	}

}
