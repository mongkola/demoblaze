package pageobject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class globWebDriver {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	
    private globWebDriver() {
        // Private constructor to prevent instantiation
    }
	
    public static WebDriver getDriver() {
        if (driver == null) {
        	String chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe";
    	    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    	    driver = new ChromeDriver();
    	    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }
        return driver;
    }
    
    public static WebDriverWait getWait() {
        if (wait == null) {
            throw new IllegalStateException("WebDriver instance has not been initialized yet.");
        }
        return wait;
    }
    
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;  // Reset the driver instance
        }
    }
   
    
}
