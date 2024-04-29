package day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class WhatIsLog {
	@Test
	void differentLogs() {
		
		given()
		
		.when()
		.get("https://www.google.com")
		
		.then()
		.log().body() // print the body of api
		
		.log().cookies() // print the cookies
		
		.log().headers()  // print the headers
		
		
		
		;
		
		
		
	}

}
