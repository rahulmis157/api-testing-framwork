package com.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.tools.ant.taskdefs.optional.extension.ExtraAttribute;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class BasicPost {

	@Test
	public void testingPost() {

		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		Response res = given().body("{\"title\": \"foo\", \"body\": \"bar\",\"userId\": 2}")
				.header("Content-type", "application/json; charset=UTF-8").when().post("posts").then().assertThat()
				.statusCode(201).and().contentType(ContentType.JSON).and().body("userId", equalTo(2)).and()
				.body("title", equalTo("foo")).extract().response();
		String resstring= res.asString();
		JsonPath jsonpth= new JsonPath(res.asString());
		boolean f = jsonpth.get("title").equals("foo");
		System.out.println("checking " + f);
		

	}
}
