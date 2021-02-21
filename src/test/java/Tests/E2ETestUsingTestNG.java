package Tests;

import base.Constants;
import base.driverSetUp;
import pages.CartPage;
import pages.HomePage;
import pages.LaptopPage;
import pages.PurchaseFormPage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class E2ETestUsingTestNG extends driverSetUp {

	HomePage home;
	LaptopPage laptop;
	CartPage cart;
	PurchaseFormPage purchase;
	
	private final String TEST_LAPTOP1 = "Sony vaio i5";
	private final String TEST_LAPTOP2 = "Dell i7 8gb";
	
	private final String TEST_NAME_ON_PURCHASE_FORM = "test";
	private final String TEST_COUNTRY_ON_PURCHASE_FORM = "US";
	private final String TEST_CITY_ON_PURCHASE_FORM = "New York";
	private final String TEST_CREDITCARD_ON_PURCHASE_FORM = "4566";
	private final String TEST_MONTH_ON_PURCHASE_FORM = "May";
	private final String TEST_YEAR_ON_PURCHASE_FORM = "2020";

	@BeforeMethod
	public void beforeTest() {
		home = new HomePage(driver);
		laptop = new LaptopPage(driver);
		cart = new CartPage(driver);
		purchase = new PurchaseFormPage(driver);
	}

	private void navigateToLaptopChooseLaptopAndAddToCart(String laptopName) throws InterruptedException {
		home.navigateToLaptopCategory();
		laptop.clickOnLaptopByName(laptopName);
		laptop.clickOnAddToCart();
		laptop.acceptAlertConfirmation();
	}

	@Test
	public void test() throws InterruptedException {

		navigateToLaptopChooseLaptopAndAddToCart(TEST_LAPTOP1);
		driver.get(Constants.homePageURL);
		navigateToLaptopChooseLaptopAndAddToCart(TEST_LAPTOP2);
		laptop.clickOnCart();
		cart.deleteByItemName(TEST_LAPTOP2);
		cart.clickOnPlaceOrderButton();
		purchase.fillPurchaseForm(TEST_NAME_ON_PURCHASE_FORM, TEST_COUNTRY_ON_PURCHASE_FORM, TEST_CITY_ON_PURCHASE_FORM, TEST_CREDITCARD_ON_PURCHASE_FORM, TEST_MONTH_ON_PURCHASE_FORM, TEST_YEAR_ON_PURCHASE_FORM);
		purchase.submitPurchaseForm();
		Assert.assertEquals(purchase.totalPurchaseAmount(), purchase.purchaseDetails());
		purchase.clickOkButton();
		
	}

}
