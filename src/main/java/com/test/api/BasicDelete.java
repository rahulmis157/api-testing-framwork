package com.test.api;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BasicDelete {
	@Test
	public void delTest() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		given().when().delete("posts/1").then().assertThat().statusCode(200).and().contentType(ContentType.JSON);

	}
}
