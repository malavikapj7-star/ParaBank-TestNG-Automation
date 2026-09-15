package Tests;


import Base.BaseTest;
import Page.DashboardPage;
import Page.Loginpage;
import utilities.ConfigReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class logintest extends BaseTest {

	@Test
	public void validLogintest() {

	    Loginpage loginPage = new Loginpage(driver);

	    String username = ConfigReader.getProperty("username");
	    String password = ConfigReader.getProperty("password");

	    System.out.println("Username: [" + username + "]");
	    System.out.println("Password exists: " + (password != null));
	    System.out.println("Password length: " +
	            (password == null ? 0 : password.length()));

	    loginPage.enterUsername(username);
	    loginPage.enterPassword(password);

	    System.out.println("Username field value: [" +
	            driver.findElement(By.name("username")).getAttribute("value") + "]");

	    System.out.println("Password field length: " +
	            driver.findElement(By.name("password")).getAttribute("value").length());

	    loginPage.clickLogin();

	    System.out.println("URL after login: " + driver.getCurrentUrl());

	    Assert.assertTrue(
	            loginPage.isLoginSuccessful(),
	            "Login failed"
	    );
	}

}