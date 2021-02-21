package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import base.Utils;

public class PurchaseFormPage extends Utils {
	

	@FindBy(xpath = "//*[@id='orderModalLabel']")
	public WebElement PurchaseFormHeader;
	
	@FindBy(xpath = "//input[@id='name']")
	public WebElement NameTextBox;
	
	@FindBy(xpath = "//input[@id='country']")
	public WebElement CountryTextBox;
	
	@FindBy(xpath = "//input[@id='city']")
	public WebElement CityTextBox;
	
	@FindBy(xpath = "//input[@id='card']")
	public WebElement CreditCardTextBox;
	
	@FindBy(xpath = "//input[@id='month']")
	public WebElement MonthTextBox;
	
	@FindBy(xpath = "//input[@id='year']")
	public WebElement YearTextBox;

	@FindBy(xpath = "//button[@onclick='purchaseOrder()']")
	public WebElement PurchaseButton;
	
	@FindBy(xpath = "//div[contains(@class, 'sweet-alert')]")
	public WebElement PurchaseDoneAlert;
	
	@FindBy(xpath = "//p[@class='lead text-muted ']")
	public WebElement PurchaseDetails;
	
	@FindBy(xpath = "//div[@class='sa-confirm-button-container']")
	public WebElement PurchaseFormOkButton;
	
	@FindBy(xpath = "//label[@id='totalm']")
	public WebElement TotalPurchasePrice;
	
	public PurchaseFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void fillPurchaseForm(String name, String country, String city, String creditCard, String month, String year) {
		
		waitForElementToBeEnabled(PurchaseFormHeader);
		NameTextBox.sendKeys(name);
		CountryTextBox.sendKeys(country);
		CityTextBox.sendKeys(city);
		CreditCardTextBox.sendKeys(creditCard);
		MonthTextBox.sendKeys(month);
		YearTextBox.sendKeys(year);
		
		log.info("Filled the purchase form with Name: "+name+"/n"+"Country: "+country+"/n"+"City: "+city+"/n"+"Credit Card Number: "+creditCard+"/n"+"Month: "+month+"/n"+"Year: "+year+"/n");
		test.log(Status.INFO, "Filled the purchase form with Name: "+name+"/n"+"Country: "+country+"/n"+"City: "+city+"/n"+"Credit Card Number: "+creditCard+"/n"+"Month: "+month+"/n"+"Year: "+year+"/n");
		
	}
	
	public void submitPurchaseForm() {
		
		waitForElementToBeEnabled(PurchaseButton);
		PurchaseButton.click();
		
		log.info("Clicked the Purchase button");
		test.log(Status.INFO, "Clicked the Purchase button");
	}
	
	public String purchaseDetails() {
		
		String purchaseID=null;
		String purchaseAmount=null;
		
		if(PurchaseDoneAlert.isDisplayed()) {
			String[] purchaseDetails = PurchaseDetails.getText().split("\n");
			purchaseID = purchaseDetails[0];
			purchaseAmount = purchaseDetails[1];
		}
		
		log.info("Purchase ID: "+purchaseID);
		log.info("Purchase Amount: "+purchaseAmount);
		
		test.log(Status.INFO, "Purchase ID: "+purchaseID);
		test.log(Status.INFO, "Purchase Amount: "+purchaseAmount);
		
		return purchaseAmount.replaceAll("[^\\d.]", "");
	}
	
	public void clickOkButton() {
		waitForElementToBeEnabled(PurchaseFormOkButton);
		PurchaseFormOkButton.click();
		
		log.info("Clicked OK button from purchase details pop-up");
		test.log(Status.INFO, "Clicked OK button from purchase details pop-up");
	}
	
	public String totalPurchaseAmount() {
		
		return TotalPurchasePrice.getText().replaceAll("[^\\d.]", "");
	}
}
