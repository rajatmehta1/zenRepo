package hdd.models.mappers;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.DenseVector;

public class DataToModelAdapterMapper implements Function<String,LabeledPoint> {

	@Override
	public LabeledPoint call(String dataRow) throws Exception {
		String newLine = dataRow.replaceAll("\\?", "99.0");
		String[] tokens = newLine.split(",");
		
		System.out.println("tokens count : " + tokens.length);
		Integer lastToken = Integer.parseInt(tokens[13]);
		
		double[] featuresDblArr = new double[13];
		for(int i = 0; i < 13; i++) {
			featuresDblArr[i] = Double.parseDouble(tokens[i]);
		}
		Vector featuresVector = new DenseVector(featuresDblArr);
		
		Double classValue = 0.0;
		if(lastToken.intValue() > 0 ) classValue = 1.0;
		
		LabeledPoint _lp = new LabeledPoint(classValue, featuresVector);
		
			return _lp;
	}

}
