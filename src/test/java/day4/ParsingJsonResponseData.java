package day4;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class ParsingJsonResponseData {
	
	/*interview question 
	 * how to parse json response data
	 * parsing means we can traverse through the json data to get required fields 
	 * or data from the json
	 * we can parse the json data by using JSONObject the detail is in APPROACH 2 
	 * means we can take any data fom the API to validate by writing condition*/
	//@Test
	void testJSONResponseData() {
		//Apporach 1 VALIDATING JSON RESPONSE USING MATCHERS
		given()
		.contentType(ContentType.JSON)
		
		.when()
		.get("http://localhost:3000/location")
		
		.then()
		.statusCode(200)
		.header("Content-Type","application/json; charset=utf-8")
		.body("country",equalTo("United Kingdom"))
		;
	}
	
	//@Test
	void validateUsingTestNGAssertion() {
		//Apporach 2 VALIDATING JSON RESPONSE USING ASSERTIONS (Without using `Then()`)
		Response res=given()
				.contentType(ContentType.JSON)
		
		
		.when()
		.get("http://localhost:3000/location");
		
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
		
    	String strName=res.jsonPath().get("country").toString();
    	Assert.assertEquals(strName,"United Kingdom");
	}
	@Test
	void validateUsingTestNGAssertion1() {
		//Apporach 2 VALIDATING JSON RESPONSE USING ASSERTIONS (Without using `Then()`)
		Response res=given()
				.contentType(ContentType.JSON)	
		.when()
		.get("https://reqres.in/api/users?page=2");
    	/*here we are converting response data in to string then we are validating
    	 *  this below validations are in advanced level*/
    	// search for first name validation
    	JSONObject jsonObject =new JSONObject(res.asString());// converting response to json object type
    	boolean value=false;
    	for(int i=0;i<jsonObject.getJSONArray("data").length();i++) { // taking jsonArray length
    	String strFirstName=jsonObject.getJSONArray("data").getJSONObject(i).get("first_name").toString();
    	if(strFirstName.equalsIgnoreCase("Michael")) {
    		value=true;	
    		System.out.println(strFirstName);
    		break;
    		
    	}
    	  	
    	/*From json array 
    		we are retreving json object (number of json object is availbale so we are taking  in json 
    		object we are searching for title in json object */
    	}
    	Assert.assertTrue(value);

 }
}
