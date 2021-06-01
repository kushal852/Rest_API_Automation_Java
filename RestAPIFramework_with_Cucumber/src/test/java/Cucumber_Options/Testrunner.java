package Cucumber_Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/Features",
		glue = "Step_Definitions",
		plugin = "json:target/jsonreports/cucumber-report.json", // giving the testrunner knowledge to generate report
		
		/*
		 * deleteplaceapi testcase needs placeid from addplaceapi testcase so deleteplaceapi testcase will fail if we run independently.
		 * to avoid the failure when  deleteplaceapi ran independently we have to use hooks concept where when ever deleteplaceapi runs
		 * independently hooks will run the addplaceapi before that as a prerequisite.
		 */
		stepNotifications=true
		
		)
public class Testrunner {
//tags = "@DeletePlace",
}
