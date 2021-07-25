package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src\\test\\resources\\eLearningApplication"},
dryRun=false,
strict=true,
glue= {"StepDefinition"},
plugin= {"html:testoutput/cucumber.html","json:testoutput/cucumber.json","junit:testoutput/cucumber.xml"})
public class TestRunnerClass {

}
