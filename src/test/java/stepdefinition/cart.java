package stepdefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.cartpage;
import pageobject.homepage;

public class cart {
	
	private cartpage cartPage;
	homepage homePage;
	
	public cart() {
		this.cartPage = new cartpage();
		homePage = new homepage();
	}
	
	@Then("customer should see product added to cart")
	public void customer_should_see_product_added_to_cart(DataTable dataTable) {
		cartPage.verifyProductAddedToCart(dataTable);
	}
	
	@When("customer place order")
	public void customer_place_order(io.cucumber.datatable.DataTable dataTable) {
		cartPage.placeOrder();
		cartPage.verifyOrderSummary(dataTable);
		cartPage.purchase();
	}
	
	@Then("customer should see purchase confirmation")
	public void customer_should_see_purchase_confirmation(DataTable dataTable) throws InterruptedException {
		cartPage.verifyPurchaseConfirmation(dataTable);
		cartPage.closePurchaseInfo();
		homePage.isCustomerAtHome();
	}

}
