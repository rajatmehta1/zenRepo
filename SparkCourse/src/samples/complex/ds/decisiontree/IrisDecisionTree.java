package samples.complex.ds.decisiontree;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.DecisionTree;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.bouncycastle.util.Arrays;

import samples.utils.Utility;
import scala.Tuple2;

/*
 * Decision Tree to evaluate iris dataset
 * Data Format :
 *  "Serial No.","Sepal.Length","Sepal.Width","Petal.Length","Petal.Width","Species"
 */
public class IrisDecisionTree {

	public static String DATA_FILE = "resources/data/iris/iris.csv";
	
	public static void main(String[] args) {
		SparkConf sconf = new SparkConf().setAppName("Decision Tree - Iris Ds").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sconf);
		
		//Lets fetch and Clean the Data at the same time
		// We are removing the first column of each row as it is just rowIndex number and not needed
		JavaRDD<String[]> dataRows = jsc.textFile(DATA_FILE).map(new Function<String, String[]>() {
			
			@Override
			public String[] call(String dataRow) throws Exception {
				String[] rowArr = dataRow.split(",");
				String[] sArr = new String[rowArr.length];
				int i = 0;
				for(int j = 1 ; j < rowArr.length ; j++) {
					//System.out.println(rowArr.toString());
					sArr[i] = rowArr[j];
					i = i + 1;
				}
				return sArr;
				
			}
			
		});
		
		
		//Note: Our model only cares for numbers so we must convert the result or classified specie of flower to number
		JavaRDD<LabeledPoint> totalData = dataRows.map(new Function<String[], LabeledPoint>() {
			@Override
			public LabeledPoint call(String[] dataRow) throws Exception {
				int arrLength = dataRow.length-1;
				double flowerLabel = -1.0;
				System.out.println("crap value ->" + dataRow[arrLength].replaceAll("\"", ""));;
				if("setosa".equals(dataRow[arrLength].replaceAll("\"", ""))) flowerLabel =1.0;
				else if("versicolor".equals(dataRow[arrLength].replaceAll("\"", ""))) flowerLabel = 2.0;
				else flowerLabel = 3.0;
				
					dataRow[arrLength] = "" + flowerLabel;
			
				double[] dblDataRow = Utility.convertStringArrToDouble(Utility.leaveLastValueFetchArr(dataRow));
				Vector featureVector = Vectors.dense(dblDataRow);
				return new LabeledPoint(flowerLabel, featureVector);
				
			}			
		});
		
		//lets split on training and test data
		JavaRDD<LabeledPoint>[] totalDataSplits = totalData.randomSplit(new double[]{0.7,0.3});
		JavaRDD<LabeledPoint> trainingData = totalDataSplits[0];
		JavaRDD<LabeledPoint> testData = totalDataSplits[1];
		
		//lets train the decision tree model now
		
		//first fix the features
		//  Empty categoricalFeaturesInfo indicates all features are continuous.
	    HashMap<Integer, Integer> categoricalFeaturesInfo = new HashMap<Integer, Integer>();
	    String impurity = "gini";
	    Integer maxDepth = 5;
	    Integer maxBins = 32;
	    int numClasses = 2;
	    
	    final DecisionTreeModel dtModel = DecisionTree.trainClassifier(trainingData, numClasses,categoricalFeaturesInfo, impurity, maxDepth, maxBins);
	    
	    //Lets get the predicted data and compared the values
	    JavaRDD<Tuple2<Double,Double>> predictedResults = testData.map(new Function<LabeledPoint, Tuple2<Double,Double>>() {

			@Override
			public Tuple2<Double, Double> call(LabeledPoint testLblData) throws Exception {
				Double predictedResult = dtModel.predict(testLblData.features());
				return new Tuple2<Double, Double>(predictedResult, testLblData.label());
			}
	    	
		});
	    
//		List<String[]> results = dataRows.collect();
//		for (String[] rst : results) {
//			System.out.println(" ------------> " + rst.toString());
//		}
		List<Tuple2<Double, Double>> results = predictedResults.collect();
		for (Tuple2<Double,Double> rst : results) {
			System.out.println(" ------------> " + rst.toString());
		}	
//		JavaRDD<LabeledPoint> labeledPoints = dataRows.map(new Function<String, LabeledPoint>() {
//			
//		});

	}

}
