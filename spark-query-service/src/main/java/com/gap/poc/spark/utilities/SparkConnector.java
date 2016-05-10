package com.gap.poc.spark.utilities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.gap.poc.spark.constants.Constants;
import com.gap.poc.spark.exception.SparkPocServiceException;

@Slf4j
@Component
public class SparkConnector {

	public String getSparkSessionId() throws SparkPocServiceException{
		String sessionContext=getSparkContext();
		JSONObject jsonObject=new JSONObject(getSparkSession());
		JSONObject session=jsonObject.getJSONObject("session");
		return session.getString("id");
	}

	private String getSparkContext() throws SparkPocServiceException {

		HttpClient httpClient = HttpClientBuilder.create().build();
		StringEntity entity;
		try {
			entity = new StringEntity(Constants.SPARK_CONTEXT_REQUEST);
		} catch (UnsupportedEncodingException e) {
			log.error("Error while creating request context", e);
			throw new SparkPocServiceException(
					"Error while creating request context", e);
		}
		HttpPost request = new HttpPost(Constants.SPARK_VM_URL);
		request.setHeader("Content-type", Constants.CONTENT_TYPE_JSON);
		request.setEntity(entity);
		HttpResponse response = null;
		try {
			response = httpClient.execute(request);
		} catch (IOException e) {
			log.error(
					"Error while executing request for creating the context", e);
			throw new SparkPocServiceException(
					"Error while executing request for creating the context", e);
		}
		try {
			return IOUtils.toString(response.getEntity().getContent());
		} catch (IOException e) {
			log.error("Error while parsing response from the context", e);
			throw new SparkPocServiceException(
					"Error while parsing response from the context", e);
		}
	}

	private String getSparkSession() throws SparkPocServiceException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(Constants.SPARK_SESSION_URL);
		request.setHeader("Content-type", Constants.CONTENT_TYPE_JSON);
		HttpResponse response = null;
		try {
			response = httpClient.execute(request);
		} catch (IOException e) {
			log.error(
					"Error while executing request for getting the session", e);
			throw new SparkPocServiceException(
					"Error while executing request for getting the session", e);
		}
		try {
			return IOUtils.toString(response.getEntity().getContent());
		} catch (IOException e) {
			log.error("Error while parsing response from the session", e);
			throw new SparkPocServiceException(
					"Error while parsing response from the session", e);
		}

	}

}
