package pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
//initialize elements in Page Object classes
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	// WebDriver variable to control the browser.
	public WebDriver driver;
   // WebDriverWait variable for implementing explicit waits
	public WebDriverWait myWait;

	 // Constructor for the BasePage class
		public BasePage(WebDriver driver)
		{
			// Assign the WebDriver instance passed to the constructor to the local driver variable
			this.driver=driver;
		    // Initialize the WebDriverWait with the driver instance and a timeout of 5 minutes
			myWait = new WebDriverWait(driver, Duration.ofMinutes(5));
			
			// Initialize the PageFactory elements for this page object.
	        // 'this' refers to the current instance of the class
			PageFactory.initElements(driver,this);
		}

}
