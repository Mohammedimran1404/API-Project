package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 * given()
 * content type, set cookies, add auth, add param, set headers info etc..
 * 
 * when()
 * get,post,put,delete
 * 
 * then()
 * validate status code, extract response, extract headers cookies & response body ..
 * */

public class HTTPRequests {
	int id;
	@Test(priority=1)
	void getUser() {
		
		given()
		
		.when()
		  .get("https://reqres.in/api/users?page=2")
		
		.then()
		   .statusCode(200) // validate the status code
		   .body("page",equalTo(2)) // validate the body has equal parameter what we have expecting
		   .log().all();  // this will print the response to console
			
	}
	@Test(priority=2)
	void createUser() {
		/*hash map is used to create the data which is sending to api*/
		HashMap data = new HashMap();
		data.put("name", "MohammedImran");
		data.put("job", "AutomationEngineer");
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		
		.then()
		.statusCode(201)
		.log().all();
	}
	
	@Test(priority=3)
	void createUserForUpdate() {
		/*hash map is used to create the data which is sending to api*/
		HashMap data = new HashMap();
		data.put("name", "MohammedImran");
		data.put("job", "Automation Engineer");
		
		id=given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
		
	}
	@Test(priority = 4,dependsOnMethods = {"createUserForUpdate"})
	 void updateUser() {
		 
		 HashMap data = new HashMap();
			data.put("name", "MohammedImran");
			data.put("job", "Test Architect");
			
			 given()
			.contentType("application/json")
			.body(data)
			
			.when()
			.put("https://reqres.in/api/users/"+id)
			
			
			.then()
			.statusCode(200)
			.log().all();
			
			
		 
	 }
	@Test(priority=5)
	void deleteUser() {
		
		given()
		
		.when()
		.delete("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(204);
	}
	

}
