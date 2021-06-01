import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import payload_files.jira_payloads;
import payload_files.payloads;

public class jira_requests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://localhost:8080/";
		SessionFilter sf = new SessionFilter();// THIS CLASS IS USEFUL WHEN A SESSION IS CREATED 
		
		// LOGIN AUTHENCATION
		given().relaxedHTTPSValidation().log().all().header("Content-Type", "application/json")
				.body(jira_payloads.Jira_loginauth("kushalgupta852","Akhil@852")).filter(sf).when().post("rest/auth/1/session")
						.then().log().all().assertThat().statusCode(200)
						.extract().response().asString();
	/*
	System.out.println(response);
	JsonPath js =Json.raw_toJson(response);
	String session_id = js.get("session.value");
	String session_name = js.getString("session.name");*/
		
		// CREATE ISSUE 
		/*String create_issue_response = given().log().all().header("Content-Type", "application/json")
				.body(jira_payloads.Jira_Creat_Issue("SITA_defect", "Not able to login into SITA eniv")).filter(sf).when().post("rest/api/2/issue")
						.then().log().all().assertThat().statusCode(201)
						.extract().response().asString();
		
		
		JsonPath js =Json.raw_toJson(create_issue_response);
		String bug_id = js.get("id");
		System.out.println(bug_id);*/
		
		String bug_id =  "10002";
		//Add comment
		 String comment_response = given().pathParam("bug_id",bug_id).log().all().header("Content-Type", "application/json")
			.body(jira_payloads.Jira_Add_comment("comment verification")).filter(sf).when().post("rest/api/2/issue/{bug_id}/comment")
					.then().log().all().assertThat().statusCode(201)
					.extract().response().asString();
		 
		System.out.println(comment_response);
		JsonPath jsg = Json.raw_toJson(comment_response);
		int comment_id = jsg.getInt("id");
		
		// Add attachement
		given().pathParam("bug_id",bug_id).log().all().header("X-Atlassian-Token", "no-check").header("Content-Type", "multipart/form-data")
		.multiPart("file",new File("jira_attachment.txt")).filter(sf).when().post("rest/api/2/issue/{bug_id}/attachments")
				.then().log().all().assertThat().statusCode(200)
				.extract().response().asString();
		
		//get issue
		String get_issue_response = given().pathParam("bug_id",bug_id)
				.queryParam("fields", "comment")
				// path parameter will navigate use to the bug which we want using id generated for the bug and query parameter will give us what ever we need in the bug.
				.log().all().filter(sf).get("rest/api/2/issue/{bug_id}")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println(get_issue_response);
		
		JsonPath js = Json.raw_toJson(get_issue_response);
		int comments_size = js.getInt("fields.comment.comments.size()");
		
		for(int i=0;i<comments_size;i++) {
			
			System.out.println(js.getInt("fields.comment.comments["+i+"].id"));
			System.out.println(js.getString("fields.comment.comments["+i+"].body"));
			if (js.getInt("fields.comment.comments["+i+"].id") == comment_id) {
				Assert.assertEquals(js.getString("fields.comment.comments["+i+"].body"),"comment verification");
			}
			
			
			
		}
		

	}

}
