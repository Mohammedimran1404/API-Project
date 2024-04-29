package day3;
import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Cookies {
	
	/*Some times when we send the request we will get the cookies & headers as
	 * part of the response*/
	
	//@Test
	void testCookies() {
		
		given()
		
		.when()
		.get("https://www.google.com/")
		/*when we are validating the cookie every time it will generate different response
		 * if it is generating different response the cookie is working fine and the the test case 
		 * get failed so it means cookies are working fine*/
		.then()
		.cookie("AEC","Ackid1RMQlPOboatioliYUc2aake17oeMtcw5kw-NrZI7my-rSA88k7QVNo")
		.statusCode(200)
		.log().all()
		;
	}
	
	@Test
	void getCookieInfo() {
		// to store the cookie
	Response res=given()
		
		.when()
		.get("https://www.google.com/");
	
//	// for single cookie value
//		String cookie_value=res.getCookie("AEC");
//		System.out.println("Cookie Value is : "+cookie_value);
		
	
	
		// get all cookie info
	
	// it will return all cookie keys and value
	Map<String,String>allCookie=res.getCookies();
	System.out.println(allCookie);
	
	// it will return the keys of cookie only
	System.out.println("Keys of cookies are : "+allCookie.keySet());
	
	// it will return the values of cookies only
	for(String k:allCookie.keySet()) {
		String cookieValue=res.getCookie(k);
		System.out.println("All cookie values are : "+cookieValue);
		
	}
		
		
	}
	
	

}
