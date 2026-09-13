package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FundTransferPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By fromAccountDropdown = By.id("fromAccountId");
    private By toAccountDropdown = By.id("toAccountId");
    private By amountField = By.id("amount");
    private By transferButton =
            By.cssSelector("input[value='Transfer']");

    private By transferCompleteMessage =
            By.xpath("//h1[contains(text(),'Transfer Complete')]");

    // Constructor
    public FundTransferPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }

    // Select From Account
    public void selectFromAccount(String account) {

        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        fromAccountDropdown
                )
        );

        wait.until(driver -> {
            Select select = new Select(dropdown);

            return select.getOptions()
                    .stream()
                    .anyMatch(option ->
                            option.getText()
                                    .trim()
                                    .equals(account)
                    );
        });

        Select select = new Select(dropdown);
        select.selectByVisibleText(account);
    }

    // Select To Account
    public void selectToAccount(String account) {

        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        toAccountDropdown
                )
        );

        wait.until(driver -> {
            Select select = new Select(dropdown);

            return select.getOptions()
                    .stream()
                    .anyMatch(option ->
                            option.getText()
                                    .trim()
                                    .equals(account)
                    );
        });

        Select select = new Select(dropdown);
        select.selectByVisibleText(account);
    }

    // Enter Amount
    public void enterAmount(String amount) {

        WebElement amountElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        amountField
                )
        );

        amountElement.clear();
        amountElement.sendKeys(amount);
    }

    // Click Transfer
    public void clickTransfer() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        transferButton
                )
        );

        button.click();
    }

    // Complete Transfer
    public void transfer(
            String fromAccount,
            String toAccount,
            String amount) {

        selectFromAccount(fromAccount);
        selectToAccount(toAccount);
        enterAmount(amount);
        clickTransfer();
    }

    // Verify Transfer
    public boolean isTransferSuccessful() {

        try {

            WebElement message = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            transferCompleteMessage
                    )
            );

            return message.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    // Debug account options
    public void printAvailableAccounts() {

        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        fromAccountDropdown
                )
        );

        Select select = new Select(dropdown);

        System.out.println(
                "========== FROM ACCOUNTS =========="
        );

        for (WebElement option : select.getOptions()) {

            System.out.println(
                    "Text: ["
                    + option.getText()
                    + "] | Value: ["
                    + option.getAttribute("value")
                    + "]"
            );
        }

        System.out.println(
                "=================================="
        );
    }
}