import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payload_files.payloads;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

public class basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		/*RestAssured RA = new RestAssured();
		RA.baseURI = " https://rahulshettyacademy.com";*/
		
		// the above step can also be done by 
		RestAssured.baseURI = "https://rahulshettyacademy.com/"; // Base URL
		
		/* Rest Assured Works on three static classes given(), when(), then()
		 * given() -->  All the input details of the API(Query parameters,headers,authorization details,json body) should be given in Given class
		 * when() --> All the details which are needed to be submitted (HTTP request type,resource)should be done in When class
		 * Then() --> Once submitted all the response validation details should be done in Then class
		 * Since all classes are static in nature we need not create objects for accessing methods inside the class
		 * log method is used to print everything in the console
		 */
		// Post method
		/*String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payloads.AddPlace_payload()).when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.18 (Ubuntu)")
				.extract().response().asString();*/
		// passing the payload as a file
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("D:\\study\\Rest API\\Section 7 - Dynamic Payloads\\Static_Payload.json")))).when().post("maps/api/place/add/json")
						.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.18 (Ubuntu)")
						.extract().response().asString();
		
		System.out.println(response);
		JsonPath js =Json.raw_toJson(response);
		String place_id = js.get("place_id");
		System.out.println(place_id);
		
		// put method
		String newaddress = "Seethammadhara, vizag";
		String put_response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json")
				.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().response().asString();
		
		System.out.println(put_response);
		
		// Get response
		String get_response = given().log().all().queryParam("key", "qaclick123").queryParam("place_id",place_id).header("Content-Type", "application/json")
				.when().get("maps/api/place/get/json")
						.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(get_response);
		JsonPath jsg = Json.raw_toJson(get_response);
		String address_updated = jsg.get("address");
		System.out.println(address_updated);
		Assert.assertEquals(address_updated, newaddress);
		
	}

}
