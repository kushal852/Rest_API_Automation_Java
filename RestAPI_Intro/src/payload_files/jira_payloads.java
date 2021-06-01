package payload_files;

public class jira_payloads {
	
	public static String Jira_loginauth(String username,String password) {
		
		String loginauth = "{ \"username\": \""+username+"\", \"password\": \""+password+"\" }";
		
		return loginauth;
	}

	public static String Jira_Creat_Issue(String bug_name,String Bug_description) {
		
		String create_issue = "{ \"fields\": {\r\n"
				+ "\r\n"
				+ "            \"project\":  {\r\n"
				+ "\r\n"
				+ "                \"key\": \"RES\"\r\n"
				+ "\r\n"
				+ "            },\r\n"
				+ "\r\n"
				+ "             \"summary\": \""+bug_name+"\",\r\n"
				+ "\r\n"
				+ "              \"description\": \""+Bug_description+"\",\r\n"
				+ "\r\n"
				+ "               \"issuetype\": {\r\n"
				+ "\r\n"
				+ "                \"name\": \"Bug\"\r\n"
				+ "\r\n"
				+ "            }\r\n"
				+ "\r\n"
				+ "       }\r\n"
				+ "\r\n"
				+ "    }";
		
		return create_issue;
	}
	
public static String Jira_Add_comment(String comment) {
		
		String add_comment = "{\r\n"
				+ "    \"body\": \""+comment+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}";
		
		return add_comment;
	}

}
