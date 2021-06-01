import static io.restassured.RestAssured.given;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import payload_files.payloads;
import pojo_classes.Getcourses;
import pojo_classes.api_course;
import pojo_classes.mobile_course;
import pojo_classes.webautomation_course;

public class OAuth_project_with_deserialization {
	
	public static void main(String[] args) {
		
		
		String URL = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g6U4l58AxqLTzVqJq_atZJ0OnC4PSFzE8SgZd7dfptwlotFBkgbmujX65CCtnLJXA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		
		String[] url_split = URL.split("&");
		String[] code_split =  url_split[0].split("code=");
		String auth_code = code_split[1];
		
		// getting access token using auth code
		String access_token_response = given().urlEncodingEnabled(false)
		.queryParam("code", auth_code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
        .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
        .queryParams("grant_type", "authorization_code")
        .queryParams("state", "verifyfjdss")
        .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
        .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
        .when().log().all()
        .post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath jsonPath = new JsonPath(access_token_response);

	    String accessToken = jsonPath.getString("access_token");

	    System.out.println(accessToken);
	    
	    // deserializing the response recieved using pojo class
	    Getcourses courses_list =    given().contentType("application/json").

	    		queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
	    		.when().get("https://rahulshettyacademy.com/getCourse.php").as(Getcourses.class);

	    		System.out.println(courses_list.getExpertise());
	    		System.out.println(courses_list.getUrl());
	    		System.out.println(courses_list.getInstructor());
	    		System.out.println(courses_list.getLinkedIn());
	    		System.out.println(courses_list.getServices());
	    		
	    		List<api_course> api_courses = courses_list.getCourses().getApi();
	    		List<mobile_course> mobile_courses = courses_list.getCourses().getMobile();
	    		List<webautomation_course> webautomation_courses = courses_list.getCourses().getWebAutomation();
	    		String[] courses = {"Selenium Webdriver Java","Cypress","Protractor"};
	    		ArrayList<String> a = new ArrayList<String>();
	    		for(int i =0;i<api_courses.size();i++) {
	    			
	    			if(api_courses.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java")) {
	    				System.out.println(api_courses.get(i).getPrice());
	    			}
	    		}
		
	    		for(int i =0;i<mobile_courses.size();i++) {
	    			
	    			
	    			System.out.println(mobile_courses.get(i).getCourseTitle()+" and price is "+mobile_courses.get(i).getPrice());
	    			
	    		}
	    		
	          for(int i =0;i<webautomation_courses.size();i++) {
	        	  
	        	  a.add(webautomation_courses.get(i).getCourseTitle());
	    			
	    			System.out.println(webautomation_courses.get(i).getCourseTitle()+" and price is "+webautomation_courses.get(i).getPrice());
	    			
	    		}
	          
	          List<String> expectedcourses = Arrays.asList(courses);
	          
	          Assert.assertTrue(a.equals(expectedcourses));
		
		
		
	}

}
