
package Step_Definitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;


import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;

import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;

import io.cucumber.junit.Cucumber;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo_classes.Locations;
import pojo_classes.google_map_serialization_pojo;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;


@RunWith(Cucumber.class)
public class StepDefinition extends Utils {
	
	RequestSpecification request;
	ResponseSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;/*
	
	*this variable is static because two or more testcases are using this variable and if it is not declared static once execution of first testcase
	 completes all the variables in this class will be set to null to avoid that we declare the variable to static.
	*/
	
	 @Given("^Add Place payload with (.+) (.+) (.+)$")
	 public void add_place_payload_with(String name, String language, String mobilenumber) throws IOException {
		
		 request = given().spec(requestspecs())
				.body(data.AddPlacePayload(name,language,mobilenumber));
	   
	}

	@When("User calls  {string} through {string} HTTP Request")
	public void user_calls_through_http_request(String resource,String httpsrequest) {
	    
		System.out.println(resource);
		APIresources resourceAPI =APIresources.valueOf(resource);// object creation for a enum class where valueof will trigger constructor
		String resourceValue = resourceAPI.getResource();
	
		if(httpsrequest.equalsIgnoreCase("POST")) {
			
			response = request.when().post(resourceValue)
					.then().assertThat().spec(responsespecs())
					.extract().response();
			
		}
		else if(httpsrequest.equalsIgnoreCase("GET")) {
			
			response = request.when().get(resourceValue)
					.then()
					.extract().response();
		
		}
		 
	}

	@Then("API call should be success with status code {int}")
	public void api_call_should_be_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		
		int statuscode = int1;
	    assertEquals(response.getStatusCode(),statuscode);
	}

	@Then("{string} in response body should be {string}")
	public void in_response_body_should_be(String keyvalue, String value) {
	    // Write code here that turns the phrase above into concrete actions
	
		assertEquals(getjsonkey_value(response, keyvalue),value);
	}
	
	
	@And("^verify place_id created has the same name (.+) using \"([^\"]*)\"$")
    public void verify_placeid_created_has_the_same_name_using_something(String expectedname, String resource) throws IOException {
		
		place_id = getjsonkey_value(response, "place_id");
		request = given().spec(requestspecs()).queryParam("place_id",place_id);
		user_calls_through_http_request(resource,"GET");
		assertEquals(getjsonkey_value(response, "name"),expectedname); 
    }
	
	
	@Given("Delete Place payload")
	public void delete_place_payload() throws IOException {
	    
		request = given().spec(requestspecs())
				.body(data.delete_place_payload(place_id));
	}
}