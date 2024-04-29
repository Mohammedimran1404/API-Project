package day4;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ValidatingYears {
	
	@Test
	void validateUsingTestNGAssertion1() {
		//Apporach 2 VALIDATING JSON RESPONSE USING ASSERTIONS (Without using `Then()`)
		Response res=given()
				.contentType(ContentType.JSON)
		
		
		.when()
		.get("https://reqres.in/api/unknown");
		
    	JSONObject jsonObject =new JSONObject(res.asString());// converting response to json object type
    	int totalyear = 0;
    	boolean value=false;
    	for(int i=0;i<jsonObject.getJSONArray("data").length();i++) { // taking jsonArray length
    	String strYear=jsonObject.getJSONArray("data").getJSONObject(i).get("year").toString();
    	int year=Integer.parseInt(strYear);
    	totalyear=totalyear+year;
    	if(totalyear==12015) {
    		value=true;
    	}
    	}
    	Assert.assertTrue(value, "total year is not matched");
    	System.out.println(totalyear);
    	
    	int statusCode=res.getStatusCode();
    	if(statusCode==200) {
    		value=true;
    		System.out.println(statusCode);
    	}
    	Assert.assertTrue(value, "Status code is not matched");
 }

}
