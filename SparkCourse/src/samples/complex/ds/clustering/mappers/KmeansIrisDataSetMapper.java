package samples.complex.ds.clustering.mappers;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

public class KmeansIrisDataSetMapper implements Function<String, Vector>{

	@Override
	public Vector call(String rowLine) throws Exception {
	      String[] tok = rowLine.split(",");
	      double[] point = new double[tok.length - 1]; // leave the label at end
	      for (int i = 0; i < point.length; ++i) {
	        point[i] = Double.parseDouble(tok[i]);
	      }
	      return Vectors.dense(point);
	}

}
