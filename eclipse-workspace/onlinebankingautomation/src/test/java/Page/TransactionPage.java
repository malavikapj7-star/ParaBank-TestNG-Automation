
package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransactionPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By accountDropdown =
            By.id("accountId");

    private By transactionIdField =
            By.id("transactionId");

    private By transactionDateField =
            By.id("transactionDate");

    private By fromDateField =
            By.id("fromDate");

    private By toDateField =
            By.id("toDate");

    private By amountField =
            By.id("amount");

    private By findByIdButton =
            By.id("findById");

    private By findByDateButton =
            By.id("findByDate");

    private By findByDateRangeButton =
            By.id("findByDateRange");

    private By findByAmountButton =
            By.id("findByAmount");

    private By resultContainer =
            By.id("resultContainer");

    private By transactionTable =
            By.id("transactionTable");

    private By transactionRows =
            By.cssSelector("#transactionBody tr");

    private By errorContainer =
            By.id("errorContainer");

    // Constructor
    public TransactionPage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    // Select account
    public void selectAccount(String accountNumber) {

        WebElement account =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                accountDropdown
                        )
                );

        Select select = new Select(account);

        select.selectByValue(accountNumber);
    }

    // Search by Transaction ID
    public void searchByTransactionId(String transactionId) {

        WebElement field =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                transactionIdField
                        )
                );

        field.clear();
        field.sendKeys(transactionId);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        findByIdButton
                )
        ).click();
    }

    // Search by Date
    public void searchByDate(String date) {

        WebElement field =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                transactionDateField
                        )
                );

        field.clear();
        field.sendKeys(date);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        findByDateButton
                )
        ).click();
    }

    // Search by Date Range
    public void searchByDateRange(
            String fromDate,
            String toDate) {

        WebElement from =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                fromDateField
                        )
                );

        WebElement to =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                toDateField
                        )
                );

        from.clear();
        from.sendKeys(fromDate);

        to.clear();
        to.sendKeys(toDate);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        findByDateRangeButton
                )
        ).click();
    }

    // Search by Amount
    public void searchByAmount(String amount) {

        WebElement field =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                amountField
                        )
                );

        field.clear();
        field.sendKeys(amount);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        findByAmountButton
                )
        ).click();
    }

    // Verify result container
    public boolean isTransactionResultDisplayed() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            resultContainer
                    )
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    // Verify transaction table
    public boolean isTransactionTableDisplayed() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            transactionTable
                    )
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    // Verify transaction rows
    public boolean areTransactionRowsDisplayed() {

        try {

            return wait.until(
                    ExpectedConditions.numberOfElementsToBeMoreThan(
                            transactionRows,
                            0
                    )
            ).size() > 0;

        } catch (Exception e) {

            return false;
        }
    }

    // Get transaction table text
    public String getTransactionTableText() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            transactionTable
                    )
            ).getText();

        } catch (Exception e) {

            return "Transaction table was not found.";
        }
    }

    // Verify error
    public boolean isErrorDisplayed() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            errorContainer
                    )
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    // Get first transaction ID
    public String getFirstTransactionId() {

        WebElement firstTransaction =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(
                                        "#transactionBody tr:first-child a"
                                )
                        )
                );

        String href =
                firstTransaction.getAttribute("href");

        return href.substring(
                href.indexOf("id=") + 3
        );
    }

    // Debug: print transaction page
    public void printTransactionPage() {

        System.out.println(
                "========== TRANSACTION PAGE =========="
        );

        System.out.println(
                "URL: " + driver.getCurrentUrl()
        );

        System.out.println(
                "TITLE: " + driver.getTitle()
        );

        System.out.println(
                driver.getPageSource()
        );

        System.out.println(
                "======================================"
        );
    }
}
