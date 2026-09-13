package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By accountTable =
            By.id("accountTable");

    private By accountRows =
            By.cssSelector("#accountTable tbody tr");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    public boolean isAccountTableDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        accountTable
                )
        ).isDisplayed();
    }

    public String getAccountTableText() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        accountTable
                )
        ).getText();
    }

    public boolean isAccountInformationDisplayed() {

        String tableText = getAccountTableText();

        return tableText.contains("Account")
                && tableText.contains("Balance")
                && tableText.contains("Available Amount");
    }
    public boolean areAccountRowsDisplayed() {

        return !wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        accountRows,
                        0
                )
        ).isEmpty();
    }
}