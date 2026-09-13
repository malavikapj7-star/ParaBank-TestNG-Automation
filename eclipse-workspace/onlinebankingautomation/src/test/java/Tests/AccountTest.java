package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import Page.AccountPage;
import Page.Loginpage;
import utilities.ConfigReader;

public class AccountTest extends BaseTest {

	@Test
	public void verifyAccountDetailsDisplayed() {

		Loginpage loginPage = new Loginpage(driver);

		loginPage.login(
		        ConfigReader.getProperty("username"),
		        ConfigReader.getProperty("password")
		);

		AccountPage accountPage = new AccountPage(driver);

		Assert.assertTrue(
		        accountPage.isAccountTableDisplayed(),
		        "Account table was not displayed"
		);

		Assert.assertTrue(
		        accountPage.isAccountInformationDisplayed(),
		        "Account Balance or Available Amount was not displayed"
		);

		Assert.assertTrue(
		        accountPage.areAccountRowsDisplayed(),
		        "No account rows were displayed"
		);
}}