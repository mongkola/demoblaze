package pageobject;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;

public class productpage {
	
	   private WebDriver webDriver_test;
	   private WebDriverWait webDriver_wait;
	   
	   homepage homePage = new homepage();

		String addToCartbutton = "//a[text()='Add to cart']";
		
		public productpage() {
			this.webDriver_test = globWebDriver.getDriver();
			this.webDriver_wait = globWebDriver.getWait();
		}
		
		public void addToCart()  {
			webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addToCartbutton))).click();
		}
		
		
}
		
		
		
		


