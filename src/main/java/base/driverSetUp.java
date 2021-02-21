package base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class driverSetUp {
	public static WebDriver driver;
	public static Logger log;
	
	protected static ExtentHtmlReporter htmlReporter;
	protected static ExtentReports extent;
	protected static ExtentTest test;

	
	@BeforeSuite
	public void beforeSuite() {
		//extent report logic
		htmlReporter = new ExtentHtmlReporter("./test-extent/extent"+System.currentTimeMillis()+".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	@BeforeTest
	@Parameters({ "url" })
	public void createDriver(@Optional("https://www.demoblaze.com/index.html") String url) {
		System.setProperty(Constants.systemKeyChrome, Constants.systemValueChrome);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		System.out.println("Page title "+driver.getTitle());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		log = Logger.getLogger("global");
	}
	
	@BeforeTest
	public void beforeTest(ITestContext ctx) {
		//extent report logic
			
				htmlReporter.config().setReportName("Test Report"+ctx.getCurrentXmlTest().getSuite().getName());
				htmlReporter.config().setDocumentTitle("Test Report");
				htmlReporter.config().setTheme(Theme.STANDARD);
				
	}
	
	@BeforeMethod
	public void before(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}
	
	@AfterMethod
	public void killDriver(ITestResult result) throws IOException {
		
		if(result.getStatus()==ITestResult.FAILURE) {
				test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(screenshot()).build());
			} 
		
		if(result.getStatus()==ITestResult.SKIP)
			test.skip(result.getThrowable().getMessage());

		extent.flush();		
	}

	@AfterTest
	public void closeDriver() {
		driver.close();
	}
	
	//to take screenshot
		public String screenshot() {

			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String destFile =".test-extent/screenshots/shot"+System.currentTimeMillis()+".jpg";
			
			try {
				FileUtils.copyFile(srcFile, new File(destFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return destFile;
		}

}
