package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber/report.html"},
		features = "src/test/resources/login.feature",
		tags = "@test",
		glue={"stepdefinitions"},
		monochrome = true,
		stepNotifications = true
		)
public class blazerunner {

}