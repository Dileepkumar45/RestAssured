 package RequestBody;
 import static io.restassured.RestAssured.*;
 import static io.restassured.matcher.RestAssuredMatchers.*;
 import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
 
 
@SuppressWarnings("unused")
public class HandlingPayloads {
     @SuppressWarnings({ "rawtypes", "unchecked" })
     
	//@Test(priority=1)
	 void postusingHashMap() {
		   
		 HashMap data=new HashMap();
		 data.put("name", "Prabhas");
		 data.put("location", "india");
		 data.put("phone", "67546949");
		 String CourseArr[]= {"php","Ruby"};
		 data.put("courses", CourseArr);
		 
		 
		 given()
		      .contentType("application/json")
		      .body(data)
		.when()
		      .post("http://localhost:3000/students")
		.then()
		      .statusCode(201)
		      .body("name",equalTo("Prabhas"))
		      .body("courses[0]",equalTo("php"))
		      .log().all();
	 }
     
   // @Test(priority=2)
     void postusingjson() {
    	 
    	 JSONObject data = new JSONObject();
    	 data.put("name","NTR");
    	 data.put("location","india");
    	 data.put("phone","6479760");
    	 String courseArr[]= {"Robot","Excel"};
    	 
    	 data.put("courses",courseArr);
    	 given()
	      .contentType("application/json")
	      .body(data.toString())
	.when()
	      .post("http://localhost:3000/students")
	.then()
	      .statusCode(201)
	      .body("name",equalTo("NTR"))
	      .body("courses[0]",equalTo("Robot"))
	      .log().all();  
     }
     
     
  //   @Test(priority=3)
     void postusingpojo() {
    	 
    	 pojo data = new pojo();
    	 data.setName("RAM");
    	 data.setLocation("INDIA");
    	 data.setPhone("568466666");
    	 
    	 String courseArr[]= {"SQL","JS"};
    	 data.setCourses(courseArr);
    	 
    	 given()
	      .contentType("application/json")
	      .body(data)
	.when()
	      .post("http://localhost:3000/students")
	.then()
	      .statusCode(201)
	      .body("name",equalTo("RAM"))
	      .body("courses[0]",equalTo("SQL"))
	      .log().all();  
     }
     
      @Test(priority=4)
     void postusingExternalJsonFile() throws FileNotFoundException {
    	 
    	 File f= new File(".\\body.json");
    	 FileReader fr= new FileReader(f);
    	 JSONTokener jt=new JSONTokener(fr);
    	 JSONObject data = new JSONObject(jt);
    	 
    	 given()
	      .contentType("application/json")
	      .body(data.toString())
	.when()
	      .post("http://localhost:3000/students")
	.then()
	      .statusCode(201)
	      .body("name",equalTo("Arjun"))
	      .body("courses[0]",equalTo("API"))
	      .log().all();  
    	 
    	 
    	 
     }
}
