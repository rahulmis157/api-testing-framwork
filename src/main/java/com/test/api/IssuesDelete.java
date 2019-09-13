package com.test.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class IssuesDelete {
	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {
		FileInputStream file = new FileInputStream

		("/home/rahul/Documents/api-test-project/api-testing-framwork/src/"
				+ "main/java/com/test/api/resource/config.properties");
		prop.load(file);
	}

	@Test
	public void issueDelete() throws IOException {
RestAssured.baseURI= "http://localhost:8080/";
Response res= given().log().all().header("Content-Type","application/json").header
("Cookie","JSESSIONID="+ ReuseMethods.getSession()).when().
delete(prop.getProperty("COMMENT_ENDPOINT")+ ReuseMethods.getIssuesId()).
then().assertThat().statusCode(204).extract().response();

	}

}
