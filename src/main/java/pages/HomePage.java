package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import base.Utils;

public class HomePage extends Utils {
	

	@FindBy(xpath = "//a[@id='cat']")
	public WebElement Categories;
	
	@FindBy(xpath= "//a[@id='itemc']")
	public List<WebElement> ListOfCategories;
	
	@FindBy(xpath = "//a[@onclick=\"byCat('notebook')\"]")
	public WebElement CategoryLaptop;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void homePageIsLoaded() {
		driver.getTitle().contains("STORE");
	}
	
	public void navigateToLaptopCategory() {
		
		waitForElementToBeEnabled(CategoryLaptop);
		CategoryLaptop.click();
		
		log.info("Laptop category was clicked successfully");
		test.log(Status.INFO, "Laptop category was clicked successfully");
	}
}
