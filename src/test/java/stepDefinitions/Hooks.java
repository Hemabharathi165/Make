package stepDefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import baseClass.BaseClass;
//Import Cucumber annotations for hooks.
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {
	// Declare a WebDriver object to control the browser.
	 WebDriver driver;
	// Declare a Properties object to store configuration properties.
	 Properties p;
	 TakesScreenshot ts;
	 
	// A Cucumber 'Before' hook that runs before each scenario.
	@Before
    public void setup() throws IOException
    {
		 // Initialize the WebDriver using a method from BaseClass
    	driver=BaseClass.initilizeBrowser();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    	// Get the Properties object from BaseClass
    	p=BaseClass.getProperties();
    	driver.get(p.getProperty("appURL"));
    	ts=(TakesScreenshot) driver;
    	
    	driver.manage().window().maximize();
	}
		
    
   
    
	 // A Cucumber 'After' hook that runs after each scenario
    @After
    public void addScreenshot(Scenario scenario) {
        
    	// this is for cucumber junit report
        if(scenario.isFailed()) {
        	
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
        	            
        }
        else {
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
        }
      
    }
 // Another 'After' hook with order specified, ensuring it runs after the first 'After' hook
    @After(order=2)
    public void tearDown(Scenario scenario) {
    	// Close the browser and end the WebDriver session
       driver.quit();
       
    }
   
}
