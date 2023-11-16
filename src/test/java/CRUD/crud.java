package CRUD;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class crud {
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Validating add place API
		RestAssured.baseURI="https://rahulshettyacademy.com/";
String response =
	        given().log().all().queryParam("key","qaclick123")
	                           .header("Content-Type","application/json")
	                           .body("{\r\n"
					                + "  \"location\": {\r\n"
					                + "    \"lat\": -38.383494,\r\n"
					                + "    \"lng\": 33.427362\r\n"
					                + "  },\r\n"
				                   	+ "  \"accuracy\": 50,\r\n"
					                + "  \"name\": \"Frontline house\",\r\n"
				                   	+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				                   	+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				                  	+ "  \"types\": [\r\n"
				                 	+ "    \"shoe park\",\r\n"
			                 		+ "    \"shop\"\r\n"
				                	+ "  ],\r\n"
				                 	+ "  \"website\": \"http://google.com\",\r\n"
				                   	+ "  \"language\": \"French-IN\"\r\n"
				                	+ "}")
	       .when().post("maps/api/place/add/json")
	       .then().assertThat().log().all().statusCode(200)
	       .body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)")
	       .extract().response().asString();
     
	       System.out.println(response);
//
	       JsonPath js = new JsonPath(response);
	       String placeId = js.getString("place_id");
	       System.out.println(placeId);
////   
////        Update a place using POST method
//	       
       String newAddress = "70 winter walk, USA";
	       given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
       .body("{\r\n"
     		+ "\"place_id\":\""+placeId+"\",\r\n"
	       		+ "\"address\":\""+newAddress+"\",\r\n"
	       		+ "\"key\":\"qaclick123\"\r\n"
	       		+ "}").when().put("maps/api/place/update/json")
	       .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
//      
//	      //Get an updated place	       
	      String getPlaceResponse= given().log().all().queryParam("key","qaclick123")
	       .queryParam("place_id",placeId)
	       .when().get("maps/api/place/get/json")
	       .then().assertThat().log().all().statusCode(200).extract().response().asString();
	       
	      JsonPath js1= new JsonPath(getPlaceResponse);
	      String actualAddress = js1.getString("address");
	      Assert.assertEquals(actualAddress, newAddress);
//	
//      // Delete an place
         given().log().all().queryParam("key","qaclick123").body("\"place_id\":\""+placeId+"\"")
             .when().delete("maps/api/place/delete/json")
             .then().assertThat().log().all().statusCode(200);
}
}