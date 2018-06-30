package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {
	
	@FindBy(id="nav-link-accountList")
	private WebElement signInButton; 

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public static HomePage open(WebDriver driver, String url)
	{
		driver.navigate().to(url);
		return new HomePage(driver);
	}

	public LoginPage clickSignIn()
	{
		Actions action = new Actions(driver);
		action.moveToElement(signInButton).click().perform();
		return new LoginPage(driver);
	}
	
}
