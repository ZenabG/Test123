package pages;

import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.aventstack.extentreports.Status;

import base.Utils;

public class LaptopPage extends Utils {

	@FindBy(xpath = "//a[contains(@onclick,'addToCart')]")
	public WebElement AddToCart;

	@FindBy(xpath = "//a[contains(@href, 'prod.html')]")
	public List<WebElement> ListOfLaptops;

	@FindBy(xpath = "//a[@id='cartur']")
	public WebElement CartButton;

	@FindBy(xpath = "//div[@class='col-lg-9']")
	public WebElement LaptopPage;

	public LaptopPage(WebDriver driver) {
		this.driver = driver;
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30); 
		PageFactory.initElements(factory, this);
	}

	public void clickOnLaptopByName(String laptopName) throws InterruptedException {
		Thread.sleep(3000);
		try {
			for (WebElement laptop : ListOfLaptops) {

				if (laptop.getText().equalsIgnoreCase(laptopName)) {
					
					laptop.click();
				}
			}

		} catch (StaleElementReferenceException e) {
			log.info("Stale Exception caught :(");

		}
		
		log.info("The laptop "+laptopName+" was clicked successfully");
		test.log(Status.INFO, "The laptop "+laptopName+" was clicked successfully");

	}

	public void clickOnAddToCart() {
		waitForElementToBeEnabled(AddToCart);
		AddToCart.click();
		
		log.info("Clicked the Add to Cart button and added the item to cart");
		test.log(Status.INFO, "Clicked the Add to Cart button and added the item to cart");
	}

	public void clickOnCart() {
		waitForElementToBeEnabled(CartButton);
		CartButton.click();
		
		log.info("Clicked the Cart button");
		test.log(Status.INFO, "Clicked the Cart button");
	}
}
