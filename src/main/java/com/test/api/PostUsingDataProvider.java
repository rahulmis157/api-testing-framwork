package com.test.api;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostUsingDataProvider {

	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fileint = new FileInputStream("/home/rahul/Documents"
				+ "/api-test-project/api-testing-framwork/src/main/java/com/test" + "/api/resource/config.properties");
		prop.load(fileint);
	}

	// Step:1
	static final String DATA_CSV_FILENAME = "/home/rahul/Documents/api-test-project/api-testing-framwork/src/main/java/com/test/api/resource/userdata.csv";

	// Step:2
	public List<User> csvReader() throws FileNotFoundException, IOException {
		try (ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(DATA_CSV_FILENAME),
				CsvPreference.STANDARD_PREFERENCE)) {
			// the header elements are used to map the values to the bean
			final String[] headers = beanReader.getHeader(true);
			// final String[] headers = new
			// String[]{"CustomerId","CustomerName","Country","PinCode","Email"};
			final CellProcessor[] processors = getProcessors();

			List<User> users = new ArrayList<>();
			User user;
			while ((user = beanReader.read(User.class, headers, processors)) != null) {
				users.add(user);
			}
			return users;
		}

	}

	private static CellProcessor[] getProcessors() {

		final CellProcessor[] processors = new CellProcessor[] { new NotNull(), new NotNull(), new NotNull() };
		return processors;
	}

	// Step:3
	@DataProvider(name = "userData")
	public Object[][] getTestData() throws FileNotFoundException, IOException {
		List<User> users = csvReader();
		Object[][] userData = new Object[users.size()][1];

		for (int i = 0; i < users.size(); i++) {
			Object[] ua = new Object[1];
			ua[0] = users.get(i);
			userData[i][0] = ua; // {{"user1", }, {user2}, {user3}}
		}
		return userData;

	}

	// Step:4
	@Test(dataProvider = "userData")
	public void testingPost(Object u) throws FileNotFoundException, IOException {

		Gson gson = new Gson();
		// TODO Auto-generated method stub
		RestAssured.baseURI = prop.getProperty("HOST");
		System.out.println(gson.toJson(u));
		Response res = given().body(gson.toJson(u)).header("Content-type", "application/json; charset=UTF-8").when()
				.post(prop.getProperty("CREATE_ENDPOINT")).then().assertThat().statusCode(201).and()
				.contentType(ContentType.JSON).log().all().extract().response();

		JsonPath jsonpth = new JsonPath(res.asString());
		Map<String, String> field1 = (HashMap<String, String>) jsonpth.get("0");
		System.out.println(field1.get("title").toString());
		System.out.println(field1.get("body").toString());
		System.out.println(field1.get("userId").toString());
	}

	// Step:5 Validaton

}
