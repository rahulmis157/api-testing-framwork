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

public class IssuesCreation {
	static Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fileint = new FileInputStream("/home/rahul/Documents"
				+ "/api-test-project/api-testing-framwork/src/main/java/com/test" + "/api/resource/config.properties");
		prop.load(fileint);

	}

	@Test
	public void issuesCreation() throws IOException {
		RestAssured.baseURI = "http://localhost:8080/";
		Response res = given().log().all().header("Content-type", "application/json")
				.header("Cookie", "JSESSIONID=" + ReuseMethods.getSession())
				.body("{\n" + "    \"fields\": {\n" + "       \"project\":\n" + "       {\n"
						+ "          \"key\": \"BAN\"\n" + "       },\n"
						+ "       \"summary\": \"Website is not working.\",\n"
						+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n"
						+ "       \"issuetype\": {\n" + "          \"name\": \"Bug\"\n" + "       }\n" + "   }\n" + "}")
				.when().post(prop.getProperty("CREATE_ENDPOINT4")).then().assertThat().statusCode(201).extract()
				.response();
		JsonPath js = ReuseMethods.rawTojson(res);
		String id = js.get("id");
		System.out.println("id value" + id);
		

	}

}
