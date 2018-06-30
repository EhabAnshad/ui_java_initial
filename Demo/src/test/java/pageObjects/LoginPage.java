package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {
	
	@FindBy(id="ap_email")
	private WebElement emailTextbox; 
	
	@FindBy(id="ap_password")
	private WebElement passwordTextbox; 
	
	@FindBy(id="signInSubmit")
	private WebElement submitButton; 
	
	@FindBy(id="createAccountSubmit")
	private WebElement signUpButton; 
	
	@FindBy(className="a-alert-heading")
	private WebElement alertHead;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public LoginPage setEmail(String email)
	{
		emailTextbox.sendKeys(email);
		return this;
	}
	
	public LoginPage setPassword(String password)
	{
		passwordTextbox.sendKeys(password);
		return this;
	}
	
	public DashboardPage login()
	{
		submitButton.click();
		return new DashboardPage(driver);
	}
	
	public SignUpPage signUp()
	{
		return new SignUpPage(driver);
	}
	
	public String getAlert()
	{
		return alertHead.getText();
	}
	
}
