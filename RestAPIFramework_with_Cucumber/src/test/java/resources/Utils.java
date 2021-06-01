package resources;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	static RequestSpecification req;
	ResponseSpecification res;
	PrintStream log;
	public RequestSpecification requestspecs() throws IOException {
		
	if(req==null) {
		//log = new PrintStream(new FileOutputStream("logging.txt",true)); -- will append the logs everytime we run the testcase but will not rewrite it.
		log = new PrintStream(new FileOutputStream("logging.txt"));
		 req = new RequestSpecBuilder().setBaseUri(globalvaluedrive("baseurl")).addHeader("Content-Type", "application/json")
		 .addFilter(RequestLoggingFilter.logRequestTo(log))
		 .addFilter(ResponseLoggingFilter.logResponseTo(log))
				.addQueryParam("key", "qaclick123").build();
		 return req;
		 }
	 return req;
	
	}
	
	public ResponseSpecification responsespecs() {
		
		
		res = new ResponseSpecBuilder()
				.expectStatusCode(200).build();
		return res;
		
	}
	
	public static String globalvaluedrive(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\91999\\eclipse-workspace\\RestAPIFramework_with_Cucumber\\GlobalData.properties");
		prop.load(fis);
		return prop.getProperty(key);
	
	
	}
	
	public String getjsonkey_value(Response response,String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
