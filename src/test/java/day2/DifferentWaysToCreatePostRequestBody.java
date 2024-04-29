package day2;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DifferentWaysToCreatePostRequestBody {
	/*
	 * Different ways to create POST request body
	 * 1 HashMap
	 * 2 POJO(Plain Old Java Object
	 * 3 Org.json
	 * 4 External json file*/
	int id;
	// if we comment the @Test annotation the whole method get skipped
	//@Test
	void postDataUsingHashMap() {
		
		// this is preferable for small set of data
		// using HashMap
		HashMap data=new HashMap();
		data.put("name", "ImranMohammed");
		data.put("location","Madhapur");
		data.put("phone","123456789");
		// storing data in array
		String coursearr[]= {"java","python"};
		//converting array data to hashmap
		data.put("course", coursearr);
		
		given()
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		
		
		.then()
		.statusCode(201)
		.body("name", equalTo("ImranMohammed"))
		.body("location", equalTo("Madhapur"))
		.body("course[0]", equalTo("java"))
		.body("course[1]",equalTo("python"))
		.header("Content-Type", "application/json; charset=utf-8") // for generating contenet type
		.log().all()
		
		;
		
	}
	//@Test
	void deleteCurrentPost() {
		
		given()
		
		.when()
		.put("http://localhost:3000/students/7")
		// in put we are deleting the api 7 which is hard coded
		
		.then()
		.statusCode(200)
		;
	}
	
	
	// post request creating using org.json library
	// for org.json we required json dependency
	
	//@Test
	void postDataUsingOrgJson() {
		
		JSONObject data= new JSONObject();
		data.put("name", "maaz");
		data.put("location", "Hafeezpet");
		data.put("phone", "7788334456");
		
		String courseArr[]= {"javascript","Typescript"};
		data.put("course", courseArr);
		
		given()
		.contentType("application/json")
		// we are converting the data to srting because the org json does not take data directly so
		// we have to convert the data to string....
		.body(data.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		
		.then()
		.statusCode(201)
		.body("name", equalTo("maaz"))
		.body("location", equalTo("Hafeezpet"))
		.body("course[0]", equalTo("javascript"))
		.body("course[1]",equalTo("Typescript"))
		.header("Content-Type", "application/json; charset=utf-8") // for generating contenet type
		.log().all()
		;
		
	}
	
	// post request body using POJO class it is a more popular most of them use
	//@Test
   void postDataUsingPojo() {
	PojoClass data=new PojoClass();
	data.setName("imran");
	data.setLocation("Hyderabad");
	data.setPhone("0099223554");
	String courseArr[]= {"java","API"};
	data.setCourse(courseArr);
		
		
		given()
		.contentType("application/json")
		// we are converting the data to srting because the org json does not take data directly so
		// we have to convert the data to string....
		.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		
		
		.then()
		.statusCode(201)
		.body("name", equalTo("imran"))
		.body("location", equalTo("Hyderabad"))
		.body("course[0]", equalTo("java"))
		.body("course[1]",equalTo("API"))
		.header("Content-Type", "application/json; charset=utf-8") // for generating contenet type
		.log().all()
		;
		
	}
	
	
	// using json external file
   
   /*first we have to create the filename.json file 
    * 1. Right click on the project 
    * 2.click on new  
    * 3. click on file add data in the file*/
   
   @Test
   void postDataUsingExternalJson() throws FileNotFoundException {
	   /* .// for current project */
	File f = new File(".//data.json");
	// for reading the file
	FileReader fr = new FileReader(f);
	// for spliting data into json 
	
	JSONTokener jt = new JSONTokener(fr);
	
	JSONObject data = new JSONObject(jt);
		
		
		given()
		.contentType("application/json")
		// we are converting the data to srting because the org json does not take data directly so
		// we have to convert the data to string....
		.body(data.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		
		.then()
		.statusCode(201)
		.body("name", equalTo("imran"))
		.body("location", equalTo("Hyderabad"))
		.body("course[0]", equalTo("java"))
		.body("course[1]",equalTo("API"))
		.header("Content-Type", "application/json; charset=utf-8") // for generating contenet type
		.log().all()
		;
		
	}
	
	
	

}
