package resources;

//enum is a special class in java which has collection of constants and methods.
// enum is very handy because if we have 100 resources which needed be retrieved we have to create 100 methods and 100 variables in a class and need to return the values 100 times.
public enum APIresources {
	
	AddplaceAPI("maps/api/place/add/json"),
	GetplaceAPI("maps/api/place/get/json"),
	DeleteplaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	
	APIresources(String resource){
		
		this.resource = resource;
		
	}
	
	public String getResource() {
		
		return resource;
	}

}
