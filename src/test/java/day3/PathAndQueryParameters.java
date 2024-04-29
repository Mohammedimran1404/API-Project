package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class PathAndQueryParameters {
	//  https://reqres.in/api/users?page=2
	
	/* https://reqres.in/ --> called as domain
	 *  api/users  --> called as path
	 *  page=2  --> called as query parameter*/
	
	/*In this session we are learning about how to seperate the 
	 * the path and query parameter from the domain 
	 * we are seperating because not to hard code in the url
	 * so we are using seperately........*/
	
	/* https://reqres.in/api/users?page=2&id=5
	 * if the url is like this then we have to write the query param as
	 * .queryparam("page",2)
	 * .queryparam("id",5)*/
	@Test
	void pathAndQueryParameters() {
		
		given()
		// the path & query param will take the data in key & value
		.pathParam("mypath","users")  //path parameter
		.queryParam("page",2)  // query parameter we have to give same key what is present in the url 
//		.queryParam("id", 11) // by this we can take particular api like 11
		.when()
		// in user place we have to give the parameter in flower brace {mypath} noo need
		// to give query parameter it will take
		.get("https://reqres.in/api/{mypath}")
		
		.then()
		.statusCode(200)
		.log().all()
		;
		
		
		
	}

}
