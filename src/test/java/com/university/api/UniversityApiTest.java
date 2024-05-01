package com.university.api;
import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UniversityApiTest {
	
	
	@BeforeTest
	public void setupUrl() {
	
		baseURI="http://127.0.0.1:4010/";
		basePath="university";

	}
	@Test
	public void getUniversityDetails() {
		given().basePath(basePath).log().all().header("api_key", "f3c84cbb-1f9a-4b87-bb5b-2d1691b24e1e")
		.param("universityName", "University of Toronto")
		.when().get().then().log().all().statusCode(200);
	}
	
	@Test
	public void createUniversityDetails() {
		String requestBody="{\r\n"
				+ "    \"Unive rsityID\": 1000,\r\n"
				+ "    \"UniversityName\": \"University of Delhi\",\r\n"
				+ "    \"UniversityLocation\": \"Toronto, Ontario, Canada\",\r\n"
				+ "    \"UniversityFounded\": 1965,\r\n"
				+ "    \"KeyPeople\": [\r\n"
				+ "        {\r\n"
				+ "            \"name\": \"John Smith\",\r\n"
				+ "            \"age\": 18,\r\n"
				+ "            \"title\": \"President\"\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		
		
		given().basePath(basePath).header("api_key", "f3c84cbb-1f9a-4b87-bb5b-2d1691b24e1e")
		.header("content", "application/json")
		.body(requestBody)
		.when().post().then().log().all().statusCode(201);
	}
	
	/*
	 * public void createUniversityDetails2() { String
	 * requestBody="{ \"UniversityName\": \"University of Delhi\",\r\n" +
	 * "    \"UniversityLocation\": \"Toronto, Ontario, Canada\",\r\n}";
	 * 
	 * 
	 * given().basePath(basePath).header("api_key",
	 * "f3c84cbb-1f9a-4b87-bb5b-2d1691b24e1e") .header("content",
	 * "application/json") .body(requestBody)
	 * .when().post().then().log().all().statusCode(201); }
	 */
	@Test
	public void deleteUniversityDetails() {
		given().basePath(basePath).header("api_key", "f3c84cbb-1f9a-4b87-bb5b-2d1691b24e1e")
		.param("universityName", "University of Toronto")
		.when().delete().then().log().all().statusCode(204);
	}

	@Test
	public void getUniversityDetailsUsingId() {
		given().header("api_key", "f3c84cbb-1f9a-4b87-bb5b-2d1691b24e1e")
		.header("content", "application/json")
		.pathParam("unive rsityID", "university687")
		.when().get("/{unive rsityID}").then().log().all().statusCode(200);
	}
	
	@Test
	public void getUniversityDetailsWrongParam() {
		given().header("api_key", "f3c84cbb-1f9a-4b87-bb5b-2d1691b24e1e")
		.header("content", "application/json")
		.when().get("/{unive rsityID}").then().log().all().statusCode(200);
	}                                                                                                                                                                            	@Test
	public void getUniversityListDetails() {
		given().basePath("universities").header("api_key", "f3c84cbb-1f9a-4b87-bb5b-2d1691b24e1e")
		.header("content", "application/json")
		.when().get().then().log().all().statusCode(200);
	}
	
	@Test
	public void updateUniversityDetails() {
		String requestBody="{\r\n"
				+ "    \"Unive rsityID\": 1000,\r\n"
				+ "    \"UniversityName\": \"University of Delhi\",\r\n"
				+ "    \"UniversityLocation\": \"Toronto, Ontario, Canada\",\r\n"
				+ "    \"UniversityFounded\": 1965,\r\n"
				+ "    \"KeyPeople\": [\r\n"
				+ "        {\r\n"
				+ "            \"name\": \"John Smith\",\r\n"
				+ "            \"age\": 18,\r\n"
				+ "            \"title\": \"President\"\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
		
		
		given().basePath(basePath).header("api_key", "f3c84cbb-1f9a-4b87-bb5b-2d1691b24e1e")
		.header("content", "application/json").pathParam("unive rsityID", "university687")
		.body(requestBody)
		.when().put("/{universityID}").then().log().all().statusCode(201);
	}
	
	@Test
	public void getUniversityDetailsUrlPathWrong() {
		given().basePath("abcd").log().all().header("api_key", "f3c84cbb-1f9a-4b87-bb5b-2d1691b24e1e")
		.param("universityName", "University of Toronto")
		.when().get().then().log().all().statusCode(200);
	}
	
	@Test
	public void deleteUniversityDetailsWrongAuth() {
		given().basePath(basePath).header("api_key", "ABCDf3c84cbb-1f9a-4b87-bb5b-2d1691b24e1e")
		.param("universityName", "University of Toronto")
		.when().delete().then().log().all().statusCode(204);
	}
	
	@Test
	public void deleteUniversityDetailsDeletedParam422() {
		given().basePath(basePath).header("api_key", "f3c84cbb-1f9a-4b87-bb5b-2d1691b24e1e")
		.when().delete().then().log().all().statusCode(204);
	}
	
	@Test
	public void deleteUniversityDetailsNoCredential401() {
		given().basePath(basePath)
		.param("universityName", "University of Toronto")
		.when().delete().then().log().all().statusCode(204);
	}
	
	
	
}
