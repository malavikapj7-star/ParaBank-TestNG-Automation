package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
        
    }
    public void openTransactions() {
        WebElement findTransactions = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Find Transactions")
                )
        );

        findTransactions.click();
    }
    // ==============================
    // OPEN FUND TRANSFER
    // ==============================

    public void openFundTransfer() {

        WebElement transferFunds = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Transfer Funds")
                )
        );

        transferFunds.click();
    }
}