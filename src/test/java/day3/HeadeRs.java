package day3;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
public class HeadeRs {
	/*In headers also some information get changed
	 * the constant information which doesnot change is 
	 * Server, content-encoding, content type, cache control,*/
	//@Test
	void validateHeaders() {
		
		given()
		
		.when()
		.get("https://www.google.com/")
		
		
		.then()
		.header("Cache-Control", "private, max-age=0")
		// .and() is not mandatory when we have multiple validations we have to add
		.and()
		.header("Content-Type", "text/html; charset=ISO-8859-1")
		.and()
		.header("Content-Encoding", "gzip")
		.and()
		.header("Server", "gws")
		
		// print all the headers
		.log().headers()
		
		;
		
		
	}
	@Test
	void getHeadersInfo() {
		
		Response res=given()
		
		.when()
		.get("https://www.google.com/");
		
		// for single header
		String header_value=res.getHeader("Server");
		System.out.println("Server value is : "+header_value);
		
		// for multiple headers
		// returns the headers keys and value
	Headers	headers_values=res.getHeaders();
	System.out.println(headers_values);
	
	
	// another way 
	
	for(Header h:headers_values) {
		
		System.out.println("Header names : "+h.getName());
		System.out.println("Header Values : "+h.getValue());
		
	}
	

		
		
	}
	

}
