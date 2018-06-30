package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedInParent extends PageBase {
	
	@FindBy(id="nav-link-accountList")
	private WebElement loggedInName;

	public LoggedInParent(WebDriver driver) {
		super(driver);
	}
	
	public String getLoggedInName()
	{
		return loggedInName.getText().split(",")[1].split("\n")[0];
	}

}
