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

	    System.out.println("Username from config: "
	            + ConfigReader.getProperty("username"));

	    System.out.println("Password loaded: "
	            + (ConfigReader.getProperty("password") != null));

	    loginPage.login(
	            ConfigReader.getProperty("username"),
	            ConfigReader.getProperty("password")
	    );

	    System.out.println("========== AFTER LOGIN ==========");
	    System.out.println("URL: " + driver.getCurrentUrl());
	    System.out.println("TITLE: " + driver.getTitle());

	    System.out.println("PAGE TEXT:");
	    System.out.println(
	            driver.findElement(By.tagName("body")).getText()
	    );

	    System.out.println("=================================");

	    Assert.assertTrue(
	            driver.getCurrentUrl().contains("overview.htm"),
	            "Login did not reach Accounts Overview"
	    );
	}
 //   @Test
   // public void invalidLoginTest() {

      //  Loginpage loginPage = new Loginpage(driver);

      //  loginPage.login(
       //         "wrong_username",
      //          "wrong_password"
      //  );

      //  Assert.assertTrue(
       //         loginPage.isLoginErrorDisplayed(),
      //          "Login error was not displayed"
    //    );
 //   }
}