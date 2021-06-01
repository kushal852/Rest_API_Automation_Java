package pojo_classes;

import java.util.List;

public class courses {
	
	private List<webautomation_course> webAutomation;
	private List<api_course> api;
	private List<mobile_course> mobile;
	
	public List<webautomation_course> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<webautomation_course> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<api_course> getApi() {
		return api;
	}
	public void setApi(List<api_course> api) {
		this.api = api;
	}
	public List<mobile_course> getMobile() {
		return mobile;
	}
	public void setMobile(List<mobile_course> mobile) {
		this.mobile = mobile;
	}
	

}
