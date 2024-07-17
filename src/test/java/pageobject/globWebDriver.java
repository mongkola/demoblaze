package pageobject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class globWebDriver {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	
    private globWebDriver() {
        // Private constructor to prevent instantiation
    }
	
    public static WebDriver getDriver() {
    	
    	// Add condition to check the current OS that run thetest...
    	String os = System.getProperty("os.name").toLowerCase();
    	String driverPath = "src/test/resources/drivers/";
    	
    	ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-remote-origin-headers");

    	if (os.contains("win")) {
            // Windows driver
    		if (driver == null) {
    			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
                System.out.println("Windows");
            	driver = new ChromeDriver(options);
        	    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    	    }
            
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix") || os.contains("mac")) {
            // Unix, Linux, or Mac driver
        	if (driver == null) {
                System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver");
                System.out.println("Linux");
            	driver = new ChromeDriver(options);
        	    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        	}
        } else {
            throw new IllegalStateException("Unsupported operating system for webdriver setup");
        }
    	
    	/*
        if (driver == null) {
        	String chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe";
    	    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    	    driver = new ChromeDriver();
    	    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }
        */
        
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
