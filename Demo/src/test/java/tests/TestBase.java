package tests;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import configurations.ApplicationConfigurations;
import dataProviders.JsonHandler;
import utilities.CaptureScreenShotHandler;


/**
 * Base class for all testcase classes
 * it contains all shared functions and suite initializations
 * Retrieves base url from testng parameters and assigne to local field to be utilized all over the suite
 * Retrieves browsers parameters to allow multi browser execution for same testcases
 * initialize profiles for the different Web drivers
 * Contains listener for execution status with implementation to capture screenshot on failure 
 */
public class TestBase 
{
	protected WebDriver driver;
	public static JsonHandler jsonTestData;


	public static FirefoxProfile firefoxProfile()
	{
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		ApplicationConfigurations.downloadPath = System.getProperty("user.dir") + File.separator + "Downloads";
		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
		firefoxProfile.setPreference("browser.download.dir", ApplicationConfigurations.downloadPath);
		firefoxProfile.setPreference("browser.download.manager.closeWhenDone", true);
		firefoxProfile.setPreference("browser.download.manager.showAlertOnComplete", false);
		firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
		firefoxProfile.setPreference("browser.download.manager.useWindow", false);
		firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		firefoxProfile.setAcceptUntrustedCertificates(true);
		firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
		return firefoxProfile;
	}

	@BeforeSuite
	@Parameters({"URL" })
	public void initSuite(@Optional("http://127.0.0.1/") String URL) 
	{

		jsonTestData = new JsonHandler();
		buildAppUrls(URL);
	}

	@BeforeTest
	@Parameters({ "browser" })
	public void getDriverForTest(@Optional("firefox") String WindowBrowser)
	{
		if (WindowBrowser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver(firefoxProfile());
		}
		else if (WindowBrowser.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "resources"
					+ File.separator + "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			driver = new ChromeDriver(options);
		}
		else if (WindowBrowser.equalsIgnoreCase("ie")) 
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + File.separator + "resources"
					+ File.separator + "IEDriverServer.exe");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(
			    InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			    true);
			driver = new InternetExplorerDriver(caps);
		} 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to(ApplicationConfigurations.baseURl);

	}

	private void buildAppUrls(String baseUrl)
	{
		ApplicationConfigurations.baseURl = baseUrl +  jsonTestData.getData("AppUrls").get("AuthUrl");
		ApplicationConfigurations.logoutUrl= baseUrl +  jsonTestData.getData("AppUrls").get("logoutUrl"); 
	}

	@AfterMethod
	public void screeshotOnFailure(ITestResult result){
		if (result.getStatus() == ITestResult.FAILURE) {
			CaptureScreenShotHandler.captureScreenshot(driver, result.getName());
		}
	}

	@AfterTest(alwaysRun=true)
	public void stopDriver() {
		driver.quit();
	}

}
