import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payload_files.payloads;

public class Book_API {
	
	
	@Test(dataProvider = "get_book_details")
	public void add_book(String isbn,String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		// Post method
	String response = given().log().all().header("Content-Type", "application/json")
				.body(payloads.AddBook_Payload(isbn,aisle)).when().post("Library/Addbook.php")
						.then().assertThat().statusCode(200)
						.extract().response().asString();
	
	System.out.println(response);
	JsonPath js =Json.raw_toJson(response);
	String ID = js.get("ID");
	String msg = js.get("Msg");
	System.out.println(ID);
    Assert.assertEquals(msg,"successfully added");
    
	}
	
	
	
	@Test(dataProvider = "get_book_details")
public void delete_book(String isbn,String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		// Post method
	String delete_response = given().log().all().header("Content-Type", "application/json")
				.body(payloads.deleteBook_Payload(isbn,aisle)).when().post("/Library/DeleteBook.php")
						.then().assertThat().statusCode(200)
						.extract().response().asString();
	
	JsonPath js =Json.raw_toJson(delete_response);
	String msg = js.get("msg");
	Assert.assertEquals(msg, "book is successfully deleted");
	
	System.out.println(delete_response);
	}
	
	@DataProvider
	public Object[][] get_book_details() {
		
		Object[][] book_details = new Object[2][2];
				
		book_details[0][0] = "zaqwewq";
		book_details[0][1] = "6543782";
		
		book_details[1][0] = "qwewqwe";
		book_details[1][1] = "9675438";
	
	return book_details;
	}

}
