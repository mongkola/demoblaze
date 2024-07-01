package pageobject;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;

public class cartpage {
	
	   private cartpage cartPage;
	   homepage homePage;
	
	   private WebDriver webDriver_test;
	   private WebDriverWait webDriver_wait;
	   
		String productNameColumn = "//tbody[@id='tbodyid']//tr//td[2][text()='REPLACE']";
		String productPriceColumn = "//tbody[@id='tbodyid']//tr//td[3][text()='REPLACE']";
		String totalPrice = "totalp";
		String placeOrderButton = "//button[text()='Place Order']";
	   
		// Order Summary
		String customerName = "//input[@id='name']";
		String country = "//input[@id='country']";
		String city = "//input[@id='city']";
		String creditCard =  "//input[@id='card']";
		String month = "//input[@id='month']";
		String year = "//input[@id='year']";
		String placeOrderModal = "orderModal";
		String total = "totalm";
		String purchaseButton = "//button[text()='Purchase']";

		
		// Purchase confirmation pop-up
		String purchaseConfirmPopUp = "//div[contains(@class, 'showSweetAlert')]";
		String purchaseInfo = purchaseConfirmPopUp + "/p";
		String okButton = "//button[text()='OK']";
		
		public cartpage (){
			this.webDriver_test = globWebDriver.getDriver();
			this.webDriver_wait = globWebDriver.getWait();
		}	

	public void verifyProductAddedToCart(DataTable dataTable) {
		
		int actualTotalPrice = 0;
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);   
		
		for (Map<String, String> row : data) {
            String item = row.get("Item");
            String price = row.get("Price");
            System.out.println("Item: " + item + ", Price: " + price);
            
            if (item.equals("Total")) {
            	// Last record in data table, do assertion to expected and actual
            	System.out.println("Compare expected to actual total price");
            	 Assert.assertEquals(actualTotalPrice, Integer.parseInt(price));
            } else {
            	// check element in the table
	            // replace text to create complete xpath
	            String newXpathProductName = productNameColumn.replace("REPLACE", item);
	            webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXpathProductName)));
	            
	            String newXpathPrice = productPriceColumn.replace("REPLACE", price);
	            webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newXpathPrice)));
	            
	            // calculate total price
	            actualTotalPrice = actualTotalPrice + Integer.parseInt(price);
            }
        }
	}
	
	public void placeOrder() {
		webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(placeOrderButton))).click();
	}
	
	public void purchase() {
		webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(purchaseButton))).click();
	}
	
	public void closePurchaseInfo () throws InterruptedException {
		Thread.sleep(2000);
		webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(okButton))).click();
		purchaseShouldBeClosed();
	}
	
	public void verifyOrderSummary(DataTable dataTable) {
        // Convert the DataTable to a List of Map to access the data
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        // Wait until Modal appears
        webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(placeOrderModal)));
        
        // Fill-in
		webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(customerName))).sendKeys(data.get("Name"));
		webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(country))).sendKeys(data.get("Country"));
		webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(city))).sendKeys(data.get("City"));
		webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(creditCard))).sendKeys(data.get("Credit card"));
		webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(month))).sendKeys(data.get("Month"));
		webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(year))).sendKeys(data.get("Year"));

	}
	
	public void verifyPurchaseConfirmation(DataTable dataTable) {
		
		// Convert the DataTable to a List of Map to access the data
        Map<String, String> data = dataTable.asMap(String.class, String.class);

		// wait for pop-up appear
        webDriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(purchaseConfirmPopUp)));

		// verify purchase info
        String actualInfo = webDriver_test.findElement(By.xpath(purchaseInfo)).getText();
        System.out.println(actualInfo);
        
		// Quick check by using contain text
        assertTrue(actualInfo, actualInfo.contains(data.get("Amount")));
        assertTrue(actualInfo, actualInfo.contains(data.get("Card Number")));
        assertTrue(actualInfo, actualInfo.contains(data.get("Name")));
        
        webDriver_wait.until(ExpectedConditions.elementToBeClickable(By.xpath(okButton)));
        
	}
	
	public void purchaseShouldBeClosed() {
		webDriver_wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(placeOrderModal)));
		webDriver_wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(purchaseConfirmPopUp)));
	}

}
