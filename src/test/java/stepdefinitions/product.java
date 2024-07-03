package stepdefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.cartpage;
import pageobject.homepage;
import pageobject.productpage;

public class product {
	private productpage productPage;
	homepage homePage;
	cartpage cartPage;
	
	//String productName = "//h2[@class='name'][text()='REPLACE']";
	//String productPrice = "//h3[@class='price-container'][contains(text(),'$REPLACE')]";

	public product() {
		this.productPage = new productpage();
		homePage = new homepage();
		cartPage = new cartpage();
	}

}
