Feature: Validating Place API's   
  
@AddPlace @Regression
Scenario Outline: Verify whether place is added successfully through AddPostAPI

Given Add Place payload with <name> <language> <Mobile_number>
When User calls  "AddplaceAPI" through "POST" HTTP Request
Then API call should be success with status code 200
And  "status" in response body should be "OK"
And  "scope" in response body should be "APP"
And verify place_id created has the same name <name> using "GetplaceAPI"

Examples:
|name									    |language				|Mobile_number|
|current address	        |Telugu			    |9994356366   |
|relative address     		|Telugu      		|9912208626   |

@DeletePlace @Regression
Scenario: Validating whether delete place API functionality is working

Given Delete Place payload
When User calls  "DeleteplaceAPI" through "POST" HTTP Request
Then API call should be success with status code 200
And  "status" in response body should be "OK"
