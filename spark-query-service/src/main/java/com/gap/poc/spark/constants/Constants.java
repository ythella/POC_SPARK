package com.gap.poc.spark.constants;

public class Constants {

	public static final String SPARK_VM_URL="http://192.168.99.100:3000/v1/contexts/mycontext";
	public static final String SPARK_CONTEXT_REQUEST="\"{\"createSQLContext\": true,\"properties\":"
            + " {\"spark.driver.cores\": \"1\",\"spark.driver.memory\": \"1g\","
            + "\"spark.executor.memory\": \"2g\",\"spark.shuffle.sort.bypassMergeThreshold\": \"8\"},"
            + "\"packages\": [\"com.databricks:spark-csv_2.10:1.3.0\"]}\"";
	public static final String CONTENT_TYPE_JSON="application/json";
	public static final String SPARK_SESSION_URL="http://localhost:3000/v1/contexts/mycontext/sessions";
}
