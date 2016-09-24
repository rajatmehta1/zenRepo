package samples.simple.filterdata.filter;

import org.apache.spark.api.java.function.Function;

public class BloodBankByStateFilter implements Function<String, Boolean>{

	@Override
	public Boolean call(String row) throws Exception {
		// TODO Auto-generated method stub
		return row.contains("New Delhi");
	}

}
