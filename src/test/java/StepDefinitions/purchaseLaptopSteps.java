package StepDefinitions;

import base.driverSetUp;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LaptopPage;

public class purchaseLaptopSteps extends driverSetUp {
	
	HomePage homePage = new HomePage(driver);
	LaptopPage laptopPage = new LaptopPage(driver);
	
	@Before
	public void openBrowser() {
		createDriver("https://www.demoblaze.com/index.html");
	}
	
	@Given("user is on home page")
	public void user_is_on_home_page() {
	    
		homePage.homePageIsLoaded();
	}
	
	@And("user clicks on laptop category")
	public void user_clicks_on_laptop_category() {
	    
		homePage.navigateToLaptopCategory();
	}
	
	@And("user selects (.*)")
	public void user_selects_a_laptop(String laptopName) throws InterruptedException {
	    
		laptopPage.clickOnLaptopByName(laptopName);
	}

	@And("user adds the laptop to cart")
	public void user_adds_the_laptop_to_cart() {
	    
		laptopPage.clickOnAddToCart();
		laptopPage.acceptAlertConfirmation();
	}

	@And("user goes to cart")
	public void user_goes_to_cart() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@And("user deletes one laptop")
	public void deletes_one_laptop() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@And("user clicks on place order")
	public void clicks_on_place_order() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@And("user fills the purchase form")
	public void fills_the_purchase_form() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("user clicks on submit button")
	public void user_clicks_on_submit_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("the order is placed")
	public void the_order_is_placed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@And("the browser is closed")
	public void the_browser_is_closed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

}
