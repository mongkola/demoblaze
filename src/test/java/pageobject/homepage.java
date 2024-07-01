package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;

import org.openqa.selenium.Alert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.*;

public class homepage {
	
	private WebDriver webDriver_test;
	private WebDriverWait webDriver_wait;
	
	// Navigation Menu
	String menuName = "//a[@class='nav-link'][contains(text(), 'REPLACE')]";
	
	// Sign-up
	String signUpLink = "signin2";
	String signUpModal = "signInModal";
	String usernameSignIn = "sign-username";
	String passwordSignIn = "sign-password";
	String closeButton = "//button[text()='Close']";
	String signUpButton = "//button[text()='Sign up']";
	//WebElement signUpLink = webDriver.findElement(By.id(id));

	// Log In
	String logInlink = "login2";
	String logInModal = "logInModal";
	String usernameLogIn = "loginusername";
	String passwordLogIn = "loginpassword";
	//String closeButtonLogIn = "//button[@aria-label='Close']";
	String logInButton = "//button[text()='Log in']";
	String welcomeUser = "nameofuser";
	
	//Current Logged-in user
	static String currentUser;
	
	//CATEGORIES MENU
	String xpathCategoryMenu = "//div[@id='contcont']//div[@class='list-group']/a[contains(text(),'REPLACE')]";
	
	//Product name
	String xpathProductName = "//h4[@class='card-title']/a[text()='REPLACE']";
	
	public homepage() {
		this.webDriver_test = globWebDriver.getDriver();
		this.webDriver_wait = globWebDriver.getWait();
	}
	
	public void goToNavigationMenu(String menu) {
		String newXpath = menuName.replace("REPLACE", menu);
		webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXpath))).click();
	}
	
	public void openDemoBlaze() {
		webDriver_test.manage().window().maximize();
		webDriver_test.get("https://www.demoblaze.com/index.html");
	}
	
	public void authenSuccess() throws InterruptedException {
		webDriver_wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(signUpLink)));
		WebElement welcomeuser = webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(welcomeUser)));
		String welcomeMessage = welcomeuser.getText();
		Assert.assertEquals("Welcome "+ currentUser , welcomeMessage);
		Thread.sleep(5000);
	}
	
	public void customerSignUpToTheStore(DataTable dataTable) {
        // Convert the DataTable to a List of Map to access the data
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        // Access the username and password from the data
        String username = data.get("username");
        String password = data.get("password");
        // Wait for element to be visible
        WebElement usernameInput = webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(signUpLink)));
        // Click element
        usernameInput.click();
        //Wait modal display
        webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(signUpModal)));
        // Enter username and password
        WebElement usernameTextbox = webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(usernameSignIn)));
        WebElement passwordTextbox = webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(passwordSignIn)));
        usernameTextbox.sendKeys(username);
        passwordTextbox.sendKeys(password);
        // Click Sign Up
        WebElement signUpbutton = webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(signUpButton)));
        signUpbutton.click();
    }
	
	public void customerLogIntotheStore(DataTable dataTable) {
		// Convert the DataTable to a List of Map to access the data
        Map<String, String> data = dataTable.asMap(String.class, String.class);
       

        // Access the username and password from the data
        String username = data.get("username");
        String password = data.get("password");
        
        //Assign current user logged-in
        currentUser = username;
        
        // Wait for element to be visible
        WebElement usernameInput = webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(logInlink)));
        // Click element
        usernameInput.click();
        //Wait modal display
        webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(logInModal)));
        // Enter username and password
        WebElement usernameTextbox = webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(usernameLogIn)));
        WebElement passwordTextbox = webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(passwordLogIn)));
        usernameTextbox.sendKeys(username);
        passwordTextbox.sendKeys(password);
        // Click Sign Up
        WebElement logInbutton = webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logInButton)));
        logInbutton.click();
        webDriver_wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(logInModal)));
	}
	
	public void alertShow(String message) {
		// Switch to the alert
		System.out.println("Before Alert");
		webDriver_wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver_test.switchTo().alert();
        // Get the text of the alert
        String alertMessage = alert.getText();
        //Assert message
        Assert.assertEquals(message, alertMessage);
        // Close the alert
        alert.accept();
	}
	
	public void goToCategories(String category) {
		String newXpath = xpathCategoryMenu.replace("REPLACE", category);
		System.out.println("category xpath = "+newXpath);
		//div[@id='contcont']//div[@class='list-group']/a[text()='Phones']
        webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXpath)));
        webDriver_test.findElement(By.xpath(newXpath)).click();
	}
	
	public void selectProductName(String name) {
		String newXpath = xpathProductName.replace("REPLACE", name);
		System.out.println("category xpath = "+newXpath);
        WebElement productName = webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXpath)));
        productName.click();
	}
	
	public void isCustomerAtHome() {
		assertEquals(webDriver_test.getCurrentUrl(), "https://www.demoblaze.com/index.html");
		}
}
