package helpers;

import org.openqa.selenium.WebDriver;

import configurations.User;
import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginHelper {

	private LoginHelper() {
		// force to use static methods
	}

	public static DashboardPage loginAsAdmin(LoginPage loginPage) {
		User adminUser = UsersHelper.GetAdminUser();
		return loginPage.setEmail(adminUser.getUsername()).setPassword(adminUser.getPassword()).login();
	}

	public static DashboardPage loginAsAdmin(WebDriver driver, String url) {
		User adminUser = UsersHelper.GetAdminUser();
		return HomePage.open(driver, url)
				.clickSignIn()
				.setEmail(adminUser.getUsername())
				.setPassword(adminUser.getPassword())
				.login();
	}

	public static DashboardPage LogMeIn(WebDriver driver, String url, String email, String password) {
		return HomePage.open(driver, url)
				.clickSignIn()				
				.setPassword(password)
				.setEmail(email)
				.login();
	}

	public static DashboardPage LogUserIn(WebDriver driver, String url, User MyUser) {
		return HomePage.open(driver, url)
				.clickSignIn()
				.setEmail(MyUser.getUsername())
				.setPassword(MyUser.getPassword())
				.login();
	}

	public static DashboardPage LogUserIn(LoginPage loginPage, User MyUser) {
		return loginPage
				.setPassword(MyUser.getPassword())
				.setEmail(MyUser.getUsername())
				.login();
	}

}
