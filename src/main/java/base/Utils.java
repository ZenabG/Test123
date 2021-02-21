package base;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Utils extends driverSetUp {

	WebDriverWait wait = new WebDriverWait(driver, 60);
	
	public boolean waitForPageToLoad(List<WebElement> element) {
		return element.get(0).isDisplayed();
	}
	
	public void acceptAlertConfirmation() {
		waitForALertToBePresent();
		driver.switchTo().alert().accept();
	}

	public void waitForElementToBeEnabled(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForALertToBePresent() {
		wait.until(ExpectedConditions.alertIsPresent());

	}

	public void scrollToAnElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public WebElement getElementByXpathContainsText(String text)
    {
    return driver.findElement(By.xpath("//*[contains(text(), text)]"));
    }
	
	public void waitForFullPageToLoad() {
	    ExpectedCondition < Boolean > pageLoad = new
	    ExpectedCondition < Boolean > () {
	        public Boolean apply(WebDriver driver) {
	            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	        }
	    };

	    Wait < WebDriver > wait = new WebDriverWait(driver, 60);
	    try {
	        wait.until(pageLoad);
	    } catch (Throwable pageLoadWaitError) {
	        Assert.assertFalse(true);
	    }
	}
	
	public String screenshot() {

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destFile ="./screenshots/shot"+System.currentTimeMillis()+".jpg";
		
		try {
			FileUtils.copyFile(srcFile, new File(destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile;
	}


}
