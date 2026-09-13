package Tests;

import Base.BaseTest;
import Page.DashboardPage;
import Page.FundTransferPage;
import Page.Loginpage;
import utilities.ConfigReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FundTransferTest extends BaseTest {

    @Test
    public void validFundTransferTest() {

        Loginpage loginPage = new Loginpage(driver);

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        DashboardPage dashboardPage =
                new DashboardPage(driver);

        dashboardPage.openFundTransfer();

        FundTransferPage transferPage =
                new FundTransferPage(driver);

        transferPage.transfer
        ("16119", "17007", "100");

        Assert.assertTrue(
                transferPage.isTransferSuccessful(),
                "Transfer confirmation was not displayed"
        );
    }
}