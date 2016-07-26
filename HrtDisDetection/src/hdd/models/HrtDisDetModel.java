package hdd.models;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.regression.LabeledPoint;

import hdd.models.mappers.DataToModelAdapterMapper;

/*
 * Heart Dis Detection model
 * 
 * This class uses a Navie Bayes model and stores it.
 * Uses the UCI Heart Disease Dataset for cleveland with 14 attributes
 * 
 */
public class HrtDisDetModel {

	public HrtDisDetModel() {
		
	}
	
	/*
	 * Usage : HrtDisDetModel <training-data-path> <path-to-store-generated-model>
	 */
	public static void main(String[] args) {
		if(args.length < 2) throw new RuntimeException(" Please Use : HrtDisDetModel <training-data-path> <path-to-store-generated-model>");
		
		String trainDataLoc = args[0];
		String modelStorageLoc = args[1];
		
		System.out.println("Building the spark context and conf");
		SparkConfAndCtxBuilder ctxBuilder = new SparkConfAndCtxBuilder();
		JavaSparkContext jctx = ctxBuilder.loadSimpleSparkContext("Heart Disease Detection App", "local");
		
		JavaRDD<String> dsLines = jctx.textFile(trainDataLoc);
		JavaRDD<LabeledPoint> _modelTrainData = dsLines.map(new DataToModelAdapterMapper());
		
		NaiveBayesModel _model = NaiveBayes.train(_modelTrainData.rdd());
			_model.save(jctx.sc(), modelStorageLoc);
			
			ctxBuilder.closeCtx();
	}
	
	
}
