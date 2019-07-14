package com.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BasicPost {
	
	@Test
	public void testingPost() {

		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		given().body("{\"title\": \"foo\", \"body\": \"bar\",\"userId\": 2}").header("Content-type", "application/json; charset=UTF-8").
		when().
		post("posts").
		then().
		assertThat()
				.statusCode(201).and().contentType(ContentType.JSON).and()
				.body("userId", equalTo(2)).and().body("title", equalTo("foo1"));

	}
}
