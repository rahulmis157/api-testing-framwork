package com.test.api;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class updatingComment {
	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {
		FileInputStream finstrem = new FileInputStream("/home/rahul/Documents"
				+ "/api-test-project/api-testing-framwork/src/main/java/com/test/api/resource/" + "config.properties");
		prop.load(finstrem);
	}

	@Test

	public void updateComment() throws IOException {
		String issueId = ReuseMethods.getIssuesId();
		RestAssured.baseURI = "http://localhost:8080/";
		Response res = given().log().all().header("Content-Type", "application/json")
				.header("Cookie", "JSESSIONID=" + ReuseMethods.getSession())
				.body("{\n"
						+ "    \"body\": \"Thisnnnnnn is a comment jjjthat only administrators can see and employee also.\",\n"
						+ "    \"visibility\": {\n" + "        \"type\": \"role\",\n"
						+ "        \"value\": \"Administrators\"\n" + "    }\n" + "}")
				.when().put(prop.getProperty("COMMENT_ENDPOINT") + issueId + "/" + "comment" + "/"
						+ ReuseMethods.getCommentIdForIssue(issueId))
				.then().log().all().assertThat().statusCode(200).extract().response();

	}

}
