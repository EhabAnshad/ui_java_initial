package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import configurations.ApplicationConfigurations;
import utilities.DocumentSettleCondition;


/**
 * This Class is a Page Base for the other Classes.
 */

public class PageBase {


	protected WebDriver driver;

	public PageBase(WebDriver driver)
	{
		PageFactory.initElements(driver, this);	
		this.driver = driver;
	}

	public WebElement fluentWait(final By selector , WebDriver driver)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(ApplicationConfigurations.driverDefaultFluentWait, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver)
			{
				return driver.findElement(selector);
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void waitForPageToLoad()
	{
		DocumentSettleCondition condition = new DocumentSettleCondition(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
		new FluentWait<WebDriver>(driver)
		.withTimeout(120, TimeUnit.SECONDS)
		.pollingEvery(1, TimeUnit.SECONDS)
		.ignoring(NoSuchElementException.class)
		.until(condition);
	}

	public void waitForJQuery(WebDriver driver) 
	{
		(new WebDriverWait(driver, 10)).until((new ExpectedCondition<Boolean>() 
		{
			public Boolean apply(WebDriver d) {
				JavascriptExecutor js = (JavascriptExecutor) d;
				return (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
			}
		}));
	}
	
	public void cleanAndSendKeys(WebElement myElement, String keysToSend)
	{
		myElement.clear();
		myElement.sendKeys(keysToSend);
		
	}
	
	public String getElementValue(WebElement element)
	{
		return element.getAttribute("Value");
	}
	
	public String getCurrentPageTitle()
	{
		return driver.getCurrentUrl();
	}


}