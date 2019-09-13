package com.test.api;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraAPI {
	
	public String session;
	
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException {
		
	}

	@Test
	public void autication() throws IOException {
		FileInputStream fileint = new FileInputStream("/home/rahul/Documents"
				+ "/api-test-project/api-testing-framwork/src/main/java/com/test" + "/api/resource/config.properties");
		prop.load(fileint);
		RestAssured.baseURI = prop.getProperty("HOST3");
		Response res = given().header("Content-type", "application/json")
				.body("{ \"username\": \"empexperiance707\", \"password\": \"L3tmed!g\" }").when()
				.post(prop.getProperty("CREATE_ENDPOINT3")).then().assertThat().statusCode(200).extract().response();

		// String resstring = res.asString();
		JsonPath jsonpth = new JsonPath(res.asString());
		session = jsonpth.get("session.value");
		System.out.println("checking " + session);

	}
	
	public String getSession() {
		return session;
	}
	

}
