package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\resources\\Features" ,
        glue = "StepDefinitions", dryRun = false,
      tags = "@smoke2",
        plugin = {"pretty"})


public class SmokeRunner {
}

//tags option will execute the tagged testcases as mentioned in your runner class