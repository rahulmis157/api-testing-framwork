package com.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BasicGet {
	Properties prop = new Properties();

	@BeforeTest
	public  void getData() throws IOException {
		FileInputStream fileint= new FileInputStream("/home/rahul/Documents"
				+ "/api-test-project/api-testing-framwork/src/main/java/com/test"
				+ "/api/resource/config.properties");
		prop.load(fileint);
	}

	
	@Test
	public void testingGet() {

		// TODO Auto-generated method stub
		RestAssured.baseURI = prop.getProperty("HOST");
		given().when().get(prop.getProperty("GET_ENDPOINT")).then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and()
				.body("userId", equalTo(1));

	}

}
