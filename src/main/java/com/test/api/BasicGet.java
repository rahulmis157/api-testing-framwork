package com.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BasicGet {
	
	@Test
	public void testingGet() {

		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		given().when().get("posts/1").then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and()
				.body("userId", equalTo(1));

	}

}
