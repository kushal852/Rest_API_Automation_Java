package Excel_integration.RestAPI_Excel_integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;




public class Book_API_using_Hashmap {
	
	@Test
	public void add_book() throws IOException {
		//String isbn,String aisle
		
		/*
		HashMap<String,Object> maptojson = new HashMap<String, Object>();
		maptojson.put("name", "rest assured");
		maptojson.put("isbn", "bdefteredfe");
		maptojson.put("aisle", "547632");
		maptojson.put("author", "Kushal");
		
		// maptojson will be directly converted from hashmap to json automatically using est assured
		RestAssured.baseURI = "http://216.10.245.166";
		
		// Post method
	String response = given().log().all().header("Content-Type", "application/json")
				.body(maptojson).when().post("Library/Addbook.php")
						.then().assertThat().statusCode(200)
						.extract().response().asString();
	
	System.out.println(response);
	JsonPath js = new JsonPath(response);
	String ID = js.get("ID");
	String msg = js.get("Msg");
	System.out.println(ID);
    Assert.assertEquals(msg,"successfully added");
    */
		Excel_Data_Drive_with_array excel_data = new Excel_Data_Drive_with_array();
		ArrayList<String>  data_from_xl =   excel_data.get_data("AddPlace", "RestAPI","Test_Data.xlsx");
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		HashMap<String,Object> maptojson = new HashMap<String, Object>();
		HashMap<String,Object> location_hasmap = new HashMap<String, Object>();
		location_hasmap.put("lat",data_from_xl.get(0));
		location_hasmap.put("lng",data_from_xl.get(1));
		// nested hashmap
		maptojson.put("location", location_hasmap);
		maptojson.put("accuracy", data_from_xl.get(2));
		maptojson.put("name", data_from_xl.get(3));
		maptojson.put("phone_number", data_from_xl.get(4));
		maptojson.put("address", data_from_xl.get(5));
		ArrayList<String> type_of_location = new ArrayList<String>();
		Object type1 = data_from_xl.get(5);
		type_of_location.add((String) type1);
		type_of_location.add((String) data_from_xl.get(6));
		//String[] type_of_location = {"beauty parlour","pharmacy"};
		// sending arraylist into hashmap
		maptojson.put("types", type_of_location);
		maptojson.put("website", data_from_xl.get(7));
		maptojson.put("language", data_from_xl.get(8));
		System.out.println(maptojson);
		
		
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(maptojson).when().post("maps/api/place/add/json")
						.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.18 (Ubuntu)")
						.extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String place_id = js.get("place_id");
		System.out.println(place_id);
		
    
	}
	
	
	


}
