package Step_Definitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class hooks {
	
	@Before("@DeletePlace")
	public void before_scenario() throws IOException {
		
		/*
		 * the script inside the hooks class will run only when the prerequisite for the certain test case is not executed before the certain test case execution. 
		 * In this case, Place_id should not be null before running deleteplaceapi execution if it is null then we should the hooks code
		 * */
		StepDefinition sd = new StepDefinition();
		if(StepDefinition.place_id == null)
		{
		sd.add_place_payload_with("kushal", "english", "9994356366");
		sd.user_calls_through_http_request("AddplaceAPI","POST");
		sd.verify_placeid_created_has_the_same_name_using_something("kushal","GetplaceAPI");
		}
	}

}
