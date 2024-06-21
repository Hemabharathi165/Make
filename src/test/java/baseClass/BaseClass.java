package baseClass;// Declares the package 'baseclass'.
//This is a way to organize your Java classes

import java.io.FileReader;//reading of data from files
import java.io.IOException;//Exception class for handling I/O related errors
import java.net.URL;//Represents a Uniform Resource Locator
import java.time.Duration;//Represents a time duration
import java.util.Properties;//Represents a collection of properties, key-value pairs
//LogManager is part of the Log4j logging utility, used to configure the logger
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
//WebDriver class to enable control of a browser running on a separate machine
import org.openqa.selenium.remote.RemoteWebDriver;

//Declaration of the BaseClass class
public class BaseClass {
    // Static variables for WebDriver, Properties, and Logger
	static WebDriver driver;
	static Properties p;
	static Logger logger;
    // Method to initialize the browser based on properties file settings	  	     
	public static WebDriver initilizeBrowser() throws IOException
	{
    // Check if the execution environment is remote
	if(getProperties().getProperty("execution_env").equalsIgnoreCase("remote"))
		{
	// Create a DesiredCapabilities object to set browser capabilities
	DesiredCapabilities capabilities = new DesiredCapabilities();
			
	//os
    if (getProperties().getProperty("os").equalsIgnoreCase("windows")) {
			    capabilities.setPlatform(Platform.WIN11);
	} else if (getProperties().getProperty("os").equalsIgnoreCase("mac")) {
			    capabilities.setPlatform(Platform.MAC);
	} else {
	System.out.println("No matching OS..");
			      }
	//browser
	switch (getProperties().getProperty("browser").toLowerCase()) {
	case "chrome":
    capabilities.setBrowserName("chrome");
    break;
    case "edge":
    capabilities.setBrowserName("MicrosoftEdge");
	break;
	default:
	System.out.println("No matching browser");
    }
            
		// Initialize the RemoteWebDriver with the specified capabilities.
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
			
		}
		 // Check if the execution environment is local
		else if(getProperties().getProperty("execution_env").equalsIgnoreCase("local"))
			{
				switch(getProperties().getProperty("browser").toLowerCase()) 
				{
				case "chrome":// Set Chrome-specific options
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
					// Initialize the ChromeDriver
			        driver=new ChromeDriver(options);
			        break;
			    case "edge":
			    	driver=new EdgeDriver();
			        break;
			    default:
			        System.out.println("No matching browser");
			        driver=null;
				}
			}
		 // Browser configurations: delete all cookies and set timeouts
		 driver.manage().deleteAllCookies(); 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		 driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(80));
		// Return the initialized WebDriver
		 return driver;
		 
	}
	// Getter method for the WebDriver instance
	public static WebDriver getDriver() {
			return driver;
		}
	// Method to load properties from the config.properties file
	public static Properties getProperties() throws IOException
	{		 
        FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
       		
        p=new Properties();
		p.load(file);
		return p;
	}
	// Method to initialize and get the Logger instance.
	public static Logger getLogger() 
	{		 
		logger=LogManager.getLogger(); //Log4j
		return logger;
	}
	
	
}
