package rest.rest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

	@RunWith(Cucumber.class)
	@CucumberOptions(features = "src/test/resources/rest.feature"
	,
	format = {"pretty", "html:target/Destination"}
			)
	public class RestRunner {
	     
	}
