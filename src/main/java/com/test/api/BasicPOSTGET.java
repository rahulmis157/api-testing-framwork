package com.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BasicPOSTGET {
	
	Properties prop = new Properties();
	@Test
	public void postGetRequest() throws IOException {
		FileInputStream fileint = new FileInputStream("/home/rahul/Documents"
			+ "/api-test-project/api-testing-framwork/src/main/java/com/test" + "/api/resource/config.properties");
		prop.load(fileint);
		
		RestAssured.baseURI = prop.getProperty("HOST1");
		Response res = given().body("{\"profile\": {\"enabled\": \"true\"}}")
				.header("Content-type", "application/json").when()
				.post(prop.getProperty("CREATE_ENDPOINT1")).then().assertThat().statusCode(200).and()
				.contentType(ContentType.TEXT)
				.extract().response();
		String resstring = res.asString();
		String expectedResponse = "Configuration updated successfully";
		
		Assert.assertEquals(expectedResponse, res.getBody().asString());
		
		
		
		
	}
	

	public void ValidatePostRequest() {
		
		RestAssured.baseURI = prop.getProperty("HOST2");
		given().when().get(prop.getProperty("GET_ENDPOINT")).then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and()
				.body("userId", equalTo(1));
	}
	

}
