package testScripts;

import static org.testng.Assert.assertTrue;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredPractice {

	public RestAssuredPractice() {
		// TODO Auto-generated constructor stub
	}

	@BeforeSuite
	public void setUp() {
		System.out.println("Before suite");
	}

	//@Test
	public void getReq() {
		RestAssured.baseURI = "https://reqres.in/";
		RestAssured.basePath = "api/unknown";
		// Response rs = (Response)
		// RestAssured.given().get().then().log().all().statusCode(200);
		Response rs = (Response) RestAssured.given().get().andReturn();
		rs.getBody().prettyPrint();
		assertTrue(rs.getStatusCode() == 200);
		rs.then().assertThat().statusCode(200).body("page", IsEqual.equalTo(1));
	}

	//@Test
	public void multipleTest() {
		RestAssured.baseURI = "http://localhost:3000";
		//RestAssured.basePath = "posts";
		// Response rs = (Response)
		// RestAssured.given().get().then().log().all().statusCode(200);
		String userData = "{\r\n"
				+ "    \"title\": \"json-server3\",\r\n"
				+ "    \"author\": \"typicode\"\r\n"
				+ "  }";
		
		Response rs = (Response) RestAssured.given().header("Content-type", "application/json").body(userData).when().post("/posts");
				rs.then().log().all().assertThat()
				.statusCode(201).body("title", Is.is("json-server3"));
		rs.getBody().prettyPrint();

	}
	
	//@Test
	public void putTest() {
		RestAssured.baseURI = "http://localhost:3000/posts/9";
		//RestAssured.basePath = "posts";
		// Response rs = (Response)
		// RestAssured.given().get().then().log().all().statusCode(200);
		String userData = "{\r\n"
				+ "    \"title\": \"json-server3\",\r\n"
				+ "    \"author\": \"typicode\"\r\n"
		
				+ "  }";
		
		Response rs = (Response) RestAssured.given().header("Content-type", "application/json").body(userData).when().put();
				rs.then().log().all().assertThat()
				.statusCode(200).body("title", Is.is("json-server3"));
		rs.getBody().prettyPrint();

	}
	@Test
	public void patchTest() {
		RestAssured.baseURI = "http://localhost:3000/posts/9";
		//RestAssured.basePath = "posts";
		// Response rs = (Response)
		// RestAssured.given().get().then().log().all().statusCode(200);
		String userData = "{\r\n"
				+ "    \"title\": \"json-server15\"\r\n"
		
				+ "  }";
		
		Response rs = (Response) RestAssured.given().header("Content-type", "application/json").body(userData).when().patch();
				rs.then().log().all().assertThat()
				.statusCode(200).body("title", Is.is("json-server15"));
		rs.getBody().prettyPrint();

	}
	
	
	//@Test
	public void deleteTest() {
		RestAssured.baseURI = "http://localhost:3000/posts/6";
		//RestAssured.basePath = "posts/4";
		// Response rs = (Response)
		// RestAssured.given().get().then().log().all().statusCode(200);
				
		Response rs = (Response) RestAssured.given().when().delete();
		rs.then().log().all().assertThat()
				.statusCode(200);
		rs.getBody().prettyPrint();

	}
}
