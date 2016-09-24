package samples.simple.groupdata.sort;

import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class StateToBloodBankMapper implements PairFunction<String, String, Integer>{

	@Override
	public Tuple2<String, Integer> call(String dataRow) throws Exception {
		String[] dataArr = dataRow.split(",");
		return new Tuple2(dataArr[1],1); // putting 1 number here as a position holding here in integer terms to specific that a blood bank exists here
	}

}
