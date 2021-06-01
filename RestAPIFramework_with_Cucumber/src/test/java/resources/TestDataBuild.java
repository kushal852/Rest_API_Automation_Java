package resources;

import java.util.ArrayList;
import java.util.List;

import pojo_classes.Locations;
import pojo_classes.google_map_serialization_pojo;

public class TestDataBuild {
	
	
	public google_map_serialization_pojo AddPlacePayload(String name, String language, String mobilenumber) {
		
		google_map_serialization_pojo serialization_pojo = new google_map_serialization_pojo();
		serialization_pojo.setAccuracy(50);
		serialization_pojo.setLanguage(language);
		serialization_pojo.setName(name);
		serialization_pojo.setPhone_number(mobilenumber);
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
		
		return serialization_pojo;
	}
	
	public String delete_place_payload(String place_id) {
		
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
	}

}
