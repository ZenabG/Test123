package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import base.Utils;

public class CartPage extends Utils {

	@FindBy(xpath = "//button[@class='btn btn-success']")
	public WebElement PlaceOrderButton;
	
	@FindBy(xpath = "//tr[@class='success']//td[2]")
	public List<WebElement> ItemName;
	
	@FindBy(xpath = "//tr[@class='success']//td[4]/a")
	public List<WebElement> ItemDeleteButton;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void deleteByItemName(String itemName) {
		
		Map<String,WebElement> map = new HashMap();
		
		//ItemName gets the name of the item and ItemDeleteButton has the WebElement to click on the delete button.
		for(int i=0;i<ItemName.size();i++) {
			map.put(ItemName.get(i).getText(), ItemDeleteButton.get(i));
		}
		
		//after comparing all the ItemNames to the itemName passed, this method clicks the respective delete button
		for (Map.Entry<String, WebElement> entry : map.entrySet()) {
			if(entry.getKey().equalsIgnoreCase(itemName)) {
				entry.getValue().click();
			}
		}
		
		log.info("Delete button was clicked and the item "+itemName+"was removed successfully");
		test.log(Status.INFO, "Delete button was clicked and the item "+itemName+"was removed successfully");
		
	}

	public void clickOnPlaceOrderButton() throws InterruptedException {
		Thread.sleep(3000);
		PlaceOrderButton.click();
		
		log.info("Clicked the Place order button to open the purchase form");
		test.log(Status.INFO, "Clicked the Place order button to open the purchase form");
	}
}
