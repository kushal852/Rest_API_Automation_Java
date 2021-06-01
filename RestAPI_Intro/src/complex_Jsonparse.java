import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import payload_files.payloads;

public class complex_Jsonparse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * 1. Print No of courses returned by API

2.Print Purchase Amount

3. Print Title of the first course

4. Print All course titles and their respective Prices

5. Print no of copies sold by Cypress Course

6. Verify if Sum of all Course prices matches with Purchase Amount
		 * */
		
		JsonPath jsg = Json.raw_toJson(payloads.Course_pratice_mockresponse());
		
		//Print No of courses returned by API
		int course_size = jsg.getInt("courses.size()");
		System.out.println(course_size);
		//Print Purchase Amount
		int purchase_amount = jsg.getInt("dashboard.purchaseAmount");
		System.out.println(purchase_amount);
		// Print Title of the first course
		String first_course_title = jsg.getString("courses[0].title");
		System.out.println(first_course_title);
		
		int initial_amount = 0;
		for(int i=0;i<course_size;i++) {
			//Print All course titles and their respective Prices
			System.out.println(jsg.getString("courses["+i+"]"));
			System.out.println(jsg.getString("courses["+i+"].title"));
			System.out.println(jsg.getString("courses["+i+"].price"));
			System.out.println(jsg.getString("courses["+i+"].copies"));
			
			initial_amount += (jsg.getInt("courses["+i+"].price") * jsg.getInt("courses["+i+"].copies"));
			}	
		
	
	for(int i=0;i<course_size;i++) {
		
		// Print no of copies sold by Cypress Course
				if(jsg.getString("courses["+i+"].title").equalsIgnoreCase("Cypress")) {
					System.out.println(jsg.getInt("courses["+i+"].copies"));
					break;
			}
		System.out.println(initial_amount);
		// Verify if Sum of all Course prices matches with Purchase Amount
		Assert.assertEquals(initial_amount, purchase_amount);
		
		
		
	}
		
		
	}

}
