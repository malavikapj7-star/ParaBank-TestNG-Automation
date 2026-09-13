package Page;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BeneficiaryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By addBeneficiaryButton =
            By.id("add-beneficiary");

    private By beneficiaryName =
            By.id("beneficiary-name");

    private By accountNumber =
            By.id("account-number");

    private By saveButton =
            By.id("save-beneficiary");

    private By successMessage =
            By.id("success-message");

    public BeneficiaryPage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    public void clickAddBeneficiary() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        addBeneficiaryButton
                )
        ).click();
    }

    public void enterBeneficiaryName(String name) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        beneficiaryName
                )
        ).sendKeys(name);
    }

    public void enterAccountNumber(String account) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        accountNumber
                )
        ).sendKeys(account);
    }

    public void clickSave() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        saveButton
                )
        ).click();
    }

    public void addBeneficiary(
            String name,
            String account) {

        clickAddBeneficiary();
        enterBeneficiaryName(name);
        enterAccountNumber(account);
        clickSave();
    }

    public String getSuccessMessage() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        successMessage
                )
        ).getText();
    }
}
