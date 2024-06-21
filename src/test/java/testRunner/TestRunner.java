package testRunner;
//JUnit and Cucumber libraries
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
//JUnit annotation to run the class with a custom runner
import io.cucumber.junit.CucumberOptions;
//JUnit to run this class with Cucumber's test runner instead of JUnit's default runner
@RunWith(Cucumber.class)
//provides configuration settings for Cucumber test runner
@CucumberOptions(
				features={".//Features/TS_001.feature",".//Features/TS_002.feature",".//Features/TS_003.feature"},
						
		//features= {".//Features/TS_004(5)_DDTest.feature"},
		//features= {".//Features/TS_004_DD.feature"},
	    //features= {"@target/rerun.txt"},
				
				//Specifies the package where the step definition methods are located
					glue="stepDefinitions",
				// Specifies the plugins used for reporting and other purposes
					
				// pretty-Makes the console output for Cucumber more readable
					plugin= {"pretty", "html:reports/myreport.html", // Generates an HTML report at the specified location
							  "rerun:target/rerun.txt",//Creates a text file with failed scenarios to rerun
							  //Integrates with ExtentReports for advanced reporting
							  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
							},
					dryRun=false,    // checks mapping between scenario steps and step definition methods
					monochrome=true,    // to avoid junk characters in output(special char)
					publish=true     // Cucumber will publish the report to its online reporting system
					// ,tags="@smoke" //used to filter which scenarios to run based on tags like @smoke.
		)
public class TestRunner {

		}
