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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CombinedTest {
	Properties prop = new Properties();

	@BeforeTest
	public  void getData() throws IOException {
		FileInputStream fileint= new FileInputStream("/home/rahul/Documents"
				+ "/api-test-project/api-testing-framwork/src/main/java/com/test"
				+ "/api/resource/config.properties");
		prop.load(fileint);
	}

	@Test
	public void test_Get() {

		// TODO Auto-generated method stub
		RestAssured.baseURI = prop.getProperty("HOST");
		given().when().get(prop.getProperty("GET_ENDPOINT")).then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and()
				.body("userId", equalTo(1));

	}
	
	@Test
	public void test_Post() {

		// TODO Auto-generated method stub
		RestAssured.baseURI = prop.getProperty("HOST");
		Response res = given().body("{\"title\": \"foo\", \"body\": \"bar\",\"userId\": 2}")
				.header("Content-type", "application/json; charset=UTF-8").when()
				.post(prop.getProperty("CREATE_ENDPOINT")).then().assertThat().statusCode(201).and()
				.contentType(ContentType.JSON).and().body("userId", equalTo(2)).and().body("title", equalTo("foo"))
				.extract().response();
		String resstring = res.asString();
		JsonPath jsonpth = new JsonPath(res.asString());
		boolean f = jsonpth.get("title").equals("foo");
		System.out.println("checking " + f);

	}
	
	@Test
	public void Test_Delete() {
		RestAssured.baseURI = prop.getProperty("HOST");
		given().when().delete(prop.getProperty("DEL_ENDPOINT")).then().assertThat().statusCode(200).and().contentType(ContentType.JSON);

	}
}
