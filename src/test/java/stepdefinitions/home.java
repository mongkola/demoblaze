package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.homepage;
import pageobject.productpage;


public class home {
	
	private homepage homePage;
	productpage productPage; 
	
	public home() {
		this.homePage = new homepage();
		productPage = new productpage();
	}

	@Given("customer navigates to Demo Blaze")
	public void customer_navigates_to_demo_blaze() {
		homePage.openDemoBlaze();
	}
	
	@When("customer sign up to the store")
	public void customer_sign_up_to_the_store(DataTable dataTable) {
		homePage.customerSignUpToTheStore(dataTable);
	}
	
	@When("customer authenticate to the store")
	public void customer_authenticate_to_the_store(DataTable dataTable) {
		homePage.customerLogIntotheStore(dataTable);
	}
	
	@Then("customer should see alert  message {string}")
	public void customer_should_see_alert_message(String expectMessage) {
		homePage.alertShow(expectMessage);
	}
	
	@Then("customer should see alert  message of added product")
	public void customer_should_see_alert_message_of_added_product() {
		homePage.alertShow("Product added.");
		homePage.goToNavigationMenu("Home");
	}
	
	@Then("customer should authenticate to the store successfully")
	public void customer_should_authenticate_to_the_store_successfully() throws InterruptedException {
		homePage.authenSuccess();
	}
	
	@When("customer add {string} as {string}")
	public void customer_add_phone(String category, String productName) {
		homePage.goToCategories(category);
		homePage.selectProductName(productName);
		productPage.addToCart();
		//System.out.println("Click Add to cart");
	}
	
	@When("customer open cart")
	public void customer_open_cart() {
		homePage.goToNavigationMenu("Cart");
	}
	
}
