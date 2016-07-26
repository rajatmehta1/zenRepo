package hdd.models;

import java.util.ArrayList;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;

/*
 * Analysis of the data using Spark SQL
 * 
 */
public class HrtDisDataAnalyze {

	public HrtDisDataAnalyze() {
		
	}
	
	public static void main(String[] args) {
		SparkConfAndCtxBuilder ctxBuilder = new SparkConfAndCtxBuilder();
		JavaSparkContext jctx = ctxBuilder.loadSimpleSparkContext("Heart Disease Data Analysis App", "local");
		SQLContext sqlCtx = new SQLContext(jctx);
		JavaRDD<String> rows = jctx.textFile("file:///C:/Users/harpr/workspace/HrtDisDetection/src/resources/full_data_cleaned.csv");
		
		String schemaString = "age sex cp trestbps chol fbs restecg thalach exang oldpeak slope ca thal num";
		List<StructField> fields = new ArrayList<>();
		for (String fieldName : schemaString.split(" ")) {
			fields.add(DataTypes.createStructField(fieldName, DataTypes.StringType, true));
		}
		
		StructType schema = DataTypes.createStructType(fields);
		JavaRDD<Row> rowRdd = rows.map(new Function<String, Row>() {

			@Override
			public Row call(String record) throws Exception {
				String[] fields = record.split(",");
				return RowFactory.create(fields[0],fields[1],fields[2],fields[3],fields[4],fields[5],fields[6],fields[7],fields[8],fields[9],fields[10],fields[11],fields[12],fields[13]);
			}
		});
		
		DataFrame df = sqlCtx.createDataFrame(rowRdd, schema);
			df.registerTempTable("heartDisData");
		
		DataFrame results = sqlCtx.sql("select min(age) from heartDisData");
		
		JavaRDD<String> jrdd = results.javaRDD().map(new Function<Row, String>() {

			@Override
			public String call(Row arg0) throws Exception {
				return arg0.toString();
			}
		});
		
		List<String> rstList = jrdd.collect();
		
		for (String rStr : rstList) {
			System.out.println(" Minimum Age : " + rStr);
		}
	}
}

