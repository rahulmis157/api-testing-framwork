package com.test.api;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReuseMethods {
	public static String session;
	static Properties prop = new Properties();

	public static String getSession() throws IOException {
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
		return session;

	}

	public static JsonPath rawTojson(Response r) {
		String respon = r.asString();
		JsonPath x = new JsonPath(respon);
		return x;

	}

	public static String getIssuesId() throws IOException {

		// static Properties prop = new Properties();
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
		System.out.println("issue id value " + id);
		return id;

	}
	public static String getCommentId() throws IOException {
		RestAssured.baseURI= "http://localhost:8080/";
		Response res = given().header("Content-Type","application/json").
				header("Cookie","JSESSIONID="+ReuseMethods.getSession()).body("{\n" + 
						"    \"body\": \"This is a comment that only administrators can see and employee also.\",\n" + 
						"    \"visibility\": {\n" + 
						"        \"type\": \"role\",\n" + 
						"        \"value\": \"Administrators\"\n" + 
						"    }\n" + 
						"}").log().all().when()
				.post(prop.getProperty("COMMENT_ENDPOINT") + ReuseMethods.getIssuesId()+"/"+"comment")
				.then().log().all().assertThat().statusCode(201).extract().response();
		JsonPath js = ReuseMethods.rawTojson(res);
		String id = js.get("id");
		System.out.println("id value" + id);
		return id;
		
		c deed
	}
	public static String getCommentIdForIssue(String issueId) throws IOException {
		RestAssured.baseURI= "http://localhost:8080/";
		Response res = given().header("Content-Type","application/json").
				header("Cookie","JSESSIONID="+ReuseMethods.getSession()).body("{\n" + 
						"    \"body\": \"This is a comment that only administrators can see and employee also.\",\n" + 
						"    \"visibility\": {\n" + 
						"        \"type\": \"role\",\n" + 
						"        \"value\": \"Administrators\"\n" + 
						"    }\n" + 
						"}").log().all().when()
				.post(prop.getProperty("COMMENT_ENDPOINT") + issueId +"/"+"comment")
				.then().log().all().assertThat().statusCode(201).extract().response();
		JsonPath js = ReuseMethods.rawTojson(res);
		String id = js.get("id");
		System.out.println("comment id value " + id);
		return id;
		
		
	}
	
}
