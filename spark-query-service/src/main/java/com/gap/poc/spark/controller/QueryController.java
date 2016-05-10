package com.gap.poc.spark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gap.poc.spark.exception.SparkPocServiceException;
import com.gap.poc.spark.utilities.SparkConnector;

@RestController
@EnableAutoConfiguration
public class QueryController {
	
	@Autowired
	private SparkConnector sparkConnector;

	@RequestMapping("/query")
	public String queryCsv() throws SparkPocServiceException{
		return this.sparkConnector.getSparkSessionId();
	}

}
