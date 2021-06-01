import io.restassured.path.json.JsonPath;

public class Json {
	
	public static JsonPath raw_toJson(String response) {
		
		JsonPath jsg = new JsonPath(response);
		return jsg;
	}

}
