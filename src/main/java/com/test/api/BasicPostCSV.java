package com.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.tools.ant.taskdefs.optional.extension.ExtraAttribute;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.mapper.factory.GsonObjectMapperFactory;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class BasicPostCSV {
	static final String CSV_FILENAME = "/home/rahul/Documents/api-test-project/api-testing-framwork/src/main/java/com/test/api/resource/userdata.csv";

	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fileint = new FileInputStream("/home/rahul/Documents"
				+ "/api-test-project/api-testing-framwork/src/main/java/com/test" + "/api/resource/config.properties");
		prop.load(fileint);
	}

	@Test
	public void testingPost() throws FileNotFoundException, IOException {

		
		User u = getTestUser();
		Gson gson = new Gson();
		// TODO Auto-generated method stub
		RestAssured.baseURI = prop.getProperty("HOST");
		System.out.println(gson.toJson(u));
		Response res = given().body(gson.toJson(u)).header("Content-type", "application/json; charset=UTF-8").when()
				.post(prop.getProperty("CREATE_ENDPOINT")).then().assertThat().statusCode(201).and()
				.contentType(ContentType.JSON).and().body("userId", equalTo("3211")).and().body("title", equalTo("xyx"))
				.extract().response();
		String resstring = res.asString();
		JsonPath jsonpth = new JsonPath(res.asString());
		boolean f = jsonpth.get("title").equals("foo");
		System.out.println("checking " + f);
	

	}

	public User getTestUser() throws FileNotFoundException, IOException {
		try (ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(CSV_FILENAME),
				CsvPreference.STANDARD_PREFERENCE)) {
			// the header elements are used to map the values to the bean
			final String[] headers = beanReader.getHeader(true);
			// final String[] headers = new
			// String[]{"CustomerId","CustomerName","Country","PinCode","Email"};
			final CellProcessor[] processors = getProcessors();

			User user;
			while ((user = beanReader.read(User.class, headers, processors)) != null) {
				return user;
			}
			return null;
		}

	}

	private static CellProcessor[] getProcessors() {

		final CellProcessor[] processors = new CellProcessor[] { new NotNull(), new NotNull(), new NotNull() };
		return processors;
	}

}
