import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo_classes.Locations;
import pojo_classes.google_map_serialization_pojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		google_map_serialization_pojo serialization_pojo = new google_map_serialization_pojo();
		serialization_pojo.setAccuracy(50);
		serialization_pojo.setLanguage("French-IN");
		serialization_pojo.setName("current address");
		serialization_pojo.setPhone_number("9994356366");
		serialization_pojo.setWebsite("http://google.com");
		//String[] types = {"beauty parlour","pharmacy"};
		List<String> type_of_location = new ArrayList<String>();
		type_of_location.add("beauty parlour");
		type_of_location.add("pharmacy");
		serialization_pojo.setTypes(type_of_location);
		Locations l = new Locations();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		serialization_pojo.setLocation(l);
		serialization_pojo.setAddress("Yendada,Vizag");
		
	
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addHeader("Content-Type", "application/json")
				.addQueryParam("key", "qaclick123").build();
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200)
				.expectBody("scope", equalTo("APP")).expectHeader("Server","Apache/2.4.18 (Ubuntu)").build();
		
		RequestSpecification request = given().log().all().spec(req)
				.body(serialization_pojo);
				
				
		Response response = request.when().post("maps/api/place/add/json")
						.then().assertThat().spec(res)
						.extract().response();

		System.out.println(response.asString());
	}

}
