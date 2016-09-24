package samples.complex.ds.clustering.mappers;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

/*
 * This mapper returns the full row of the data as string arrays...
 * This we have simply used for testing how good the clustering is
 */
public class KmeansIrisDataSetMapperFull implements Function<String, String[]>{

	@Override
	public String[] call(String rowLine) throws Exception {
	      String[] values = rowLine.split(",");
	      	return values;
	}

}
