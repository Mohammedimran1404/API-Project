package day5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {
	/*in XML forvalidating the body we have to write the x-path 
	 * to the body for what part we are validating 
	 * the difference b/w selenium x-path and API x-path
	 * is in selenium one we use // for next nodes
	 * in this one we use .(dot)
	 * in selenium x-path index starts from 1
	 * here index starts from 0*/
	
	
	// APPROACH 1 
//	@Test
	void parsingXMLResponse() {
		
		given()
		
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		
		
		.then()
		.statusCode(200)
		.header("Content-Type", "application/xml; charset=utf-8")
		.body("TravelerinformationResponse.page",equalTo("1"))
		.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"))
		;	
	}
	//APPROACH 2
	//@Test
	void parsingXMLResponse2() {
		
		
     Response response=given()
		
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1");
     
         Assert.assertEquals(response.getStatusCode(),200);
         Assert.assertEquals(response.getHeader("Content-Type"), "application/xml; charset=utf-8");
       String strPageNo=  response.xmlPath().get("TravelerinformationResponse.page").toString();
       Assert.assertEquals(strPageNo, "1");
       
       String strName=  response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
       Assert.assertEquals(strName, "Developer");
       
       
         
	}
	
	@Test
	void parsingXMLResponse3() {
		
		
     Response response=given()
		
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1");
     
     XmlPath xmlpath=new XmlPath(response.asString()); // asString is used to convert the entire response to string
     // we use asString method;
     // to string for particular thing
   List<String> strTravellerName= xmlpath.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
     boolean value=false;
     for(int i=0;i<strTravellerName.size();i++) {
    	 System.out.println(strTravellerName.get(i));
    	 if(strTravellerName.get(i).equalsIgnoreCase("karen")) {
    		 System.out.println(strTravellerName.get(i));
    		 value=true;
    		 break;
    	 }
     }
     Assert.assertTrue(value, "name is not present");
     
     
	}
}
