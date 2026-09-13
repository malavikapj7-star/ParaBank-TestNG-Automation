package Tests;

import Base.BaseTest;
import Page.DashboardPage;
import Page.Loginpage;
import Page.TransactionPage;
import Page.FundTransferPage;
import utilities.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TransactionHistoryTest extends BaseTest {

    @Test
    public void verifyTransactionHistory() {

        // Login
        Loginpage loginPage = new Loginpage(driver);

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // Dashboard
        DashboardPage dashboardPage = new DashboardPage(driver);

        // Open Transfer Funds
        dashboardPage.openFundTransfer();

        // Create a transaction
        FundTransferPage transferPage = new FundTransferPage(driver);

        transferPage.transfer(
                "16119",
                "17007",
                "100"
        );

        // Open Find Transactions
        dashboardPage.openTransactions();

        // Create TransactionPage object
        TransactionPage transactionPage =
                new TransactionPage(driver);

        // Select source account
        transactionPage.selectAccount("16119");

        // Search transaction by amount
        transactionPage.searchByAmount("100");

        // Verify result container
        Assert.assertTrue(
                transactionPage.isTransactionResultDisplayed(),
                "Transaction result was not displayed"
        );

        // Verify transaction table
        Assert.assertTrue(
                transactionPage.isTransactionTableDisplayed(),
                "Transaction table was not displayed"
        );

        // Print transaction history
        System.out.println(
                "========== TRANSACTION HISTORY =========="
        );

        System.out.println(
                transactionPage.getTransactionTableText()
        );

        System.out.println(
                "========================================="
        );

        // Verify transaction rows
        Assert.assertTrue(
                transactionPage.areTransactionRowsDisplayed(),
                "No transaction rows were displayed"
        );
    }
}