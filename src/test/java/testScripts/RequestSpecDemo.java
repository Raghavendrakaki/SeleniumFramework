package testScripts;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestSpecDemo {

	public RequestSpecDemo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static RequestSpecification getConnectionSpec() {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBasePath("/posts");
		builder.setBaseUri("http://localhost:3000");
		RequestSpecification rs = builder.build();
		return rs;
	}
	public static ResponseSpecification getResponseSpec() {
		ResponseSpecBuilder builder = new ResponseSpecBuilder();
		builder.expectStatusCode(200);
		ResponseSpecification rs= builder.build();
		return rs;
	}
	@Test
	public void testMethod() {
		tesWithoutParam();
		tesWithParam();
	}
	static void tesWithoutParam() {
		Response res = RestAssured.given().spec(getConnectionSpec()).when().get();
		res.then().spec(getResponseSpec());
		res.getBody().prettyPrint();
	}static void tesWithParam() {
		Response res = RestAssured.given().spec(getConnectionSpec()).queryParam("id",12).when().get();
		res.then().spec(getResponseSpec());
		res.getBody().prettyPrint();
	}

}
