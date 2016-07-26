package hdd.models.mappers;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.DenseVector;


public class TestDataToFeatureVectorMapper implements Function<String,Vector> {

	@Override
	public Vector call(String arg0) throws Exception {
		String[] tokens = arg0.split(",");
		double[] darr = new double[13];
		for(int i = 0 ; i < 13 ; i++) {
			darr[i] = Double.parseDouble(tokens[i]);
		}
		
		Vector fv = new DenseVector(darr);
			return fv;
	}

}
