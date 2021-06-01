package payload_files;

public class payloads {
	
	public static String  AddPlace_payload() {
		
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Krishna Brindavanam Apartments, Yendada, Vizag, 530045\",\r\n"
				+ "  \"phone_number\": \"(+91) 9994356366\",\r\n"
				+ "  \"address\": \"Flat No. 402\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"Beauty Parlour\",\r\n"
				+ "    \"beauty\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String AddBook_Payload(String isbn, String aisle) {
		
		String Addbook = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"harry potter\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"jk rowling\"\r\n"
				+ "}";
		
		return Addbook;
	}
	
public static String deleteBook_Payload(String isbn, String aisle) {
		
		String deleteBook = "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+isbn+aisle+"\"\r\n"
				+ " \r\n"
				+ "}";
		
		return deleteBook;
	}



	
	public static String Course_pratice_mockresponse() {
		
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "";
	}
}
