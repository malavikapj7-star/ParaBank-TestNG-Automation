package com.parabank.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Bankingpage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[@id='leftPanel']//a[normalize-space()='Accounts Overview']")
    WebElement accountsoverviewlink;

    @FindBy(xpath = "//table[@id='accountTable']")
    WebElement accountstable;

    @FindBy(xpath = "//div[@id='leftPanel']//a[normalize-space()='Open New Account']")
    WebElement opennewaccountlink;

    @FindBy(xpath = "//select[@id='type']")
    WebElement accounttype;

    @FindBy(xpath = "//select[@id='fromAccountId']")
    WebElement fromaccount;

    @FindBy(xpath = "//div[@id='openAccountForm']//input[@type='button' and @value='Open New Account']")
    WebElement openaccountbutton;

    @FindBy(xpath = "//*[@id='newAccountId']")
    WebElement newaccountid;

    @FindBy(xpath = "//div[@id='leftPanel']//a[normalize-space()='Transfer Funds']")
    WebElement transferfundslink;

    @FindBy(xpath = "//input[@id='amount']")
    WebElement amountfield;

    @FindBy(xpath = "//select[@id='toAccountId']")
    WebElement toaccount;

    @FindBy(xpath = "//input[@type='submit' and @value='Transfer']")
    WebElement transferbutton;

    @FindBy(xpath = "//div[@id='leftPanel']//a[normalize-space()='Bill Pay']")
    WebElement billpaylink;
    
    @FindBy(xpath = "//div[@id='billpayForm']//input[@name='amount']")
    WebElement billpayamount;

    @FindBy(xpath = "//input[@name='payee.name']")
    WebElement payeename;

    @FindBy(xpath = "//input[@name='payee.address.street']")
    WebElement payeestreet;

    @FindBy(xpath = "//input[@name='payee.address.city']")
    WebElement payeecity;

    @FindBy(xpath = "//input[@name='payee.address.state']")
    WebElement payeestate;

    @FindBy(xpath = "//input[@name='payee.address.zipCode']")
    WebElement payeezipcode;

    @FindBy(xpath = "//input[@name='payee.phoneNumber']")
    WebElement payeephone;

    @FindBy(xpath = "//input[@name='payee.accountNumber']")
    WebElement payeeaccount;

    @FindBy(xpath = "//input[@name='verifyAccount']")
    WebElement verifyaccount;

    @FindBy(xpath = "//select[@name='fromAccountId']")
    WebElement billpayfromaccount;

    @FindBy(xpath = "//div[@id='billpayForm']//input[@type='button' and @value='Send Payment']")
    WebElement sendpaymentbutton;
    
    @FindBy(xpath = "//div[@id='leftPanel']//a[normalize-space()='Find Transactions']")
    WebElement findtransactionslink;

    @FindBy(xpath = "//select[@id='accountId']")
    WebElement searchaccount;

    @FindBy(xpath = "//*[@id='findByAmount']")
    WebElement findbyamountbutton;

    @FindBy(xpath = "//div[@id='leftPanel']//a[normalize-space()='Log Out']")
    WebElement logoutlink;

    public Bankingpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public boolean accountsOverviewDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(accountstable)).isDisplayed();
    }

    public String getFirstAccountNumber() {
        By firstAccountLink =
                By.xpath("(//table[@id='accountTable']//a)[1]");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstAccountLink))
                .getText()
                .trim();
    
    }

    public void openNewCheckingAccount(String existingAccountNumber) {
        wait.until(ExpectedConditions.elementToBeClickable(opennewaccountlink)).click();

        wait.until(ExpectedConditions.visibilityOf(accounttype));
        new Select(accounttype).selectByVisibleText("CHECKING");

        String accountNumber = existingAccountNumber.trim();

        // Wait for the account to appear in the dropdown.
        wait.until(driver -> new Select(fromaccount)
                .getOptions()
                .stream()
                .anyMatch(option ->
                        accountNumber.equals(option.getAttribute("value"))));

        new Select(fromaccount).selectByValue(accountNumber);

        wait.until(ExpectedConditions.elementToBeClickable(openaccountbutton)).click();
        wait.until(ExpectedConditions.visibilityOf(newaccountid));
    }
    public String getNewAccountNumber() {
        return wait.until(ExpectedConditions.visibilityOf(newaccountid)).getText();
    }

    public void transferFunds(String amount, String from, String to) {
        wait.until(ExpectedConditions.elementToBeClickable(transferfundslink)).click();

        WebElement amountInput =
                wait.until(ExpectedConditions.visibilityOf(amountfield));
        amountInput.clear();
        amountInput.sendKeys(amount);

        selectAccountWhenLoaded(fromaccount, from);
        selectAccountWhenLoaded(toaccount, to);

        wait.until(ExpectedConditions.elementToBeClickable(transferbutton)).click();
    }

    public void payBill(
            String name,
            String street,
            String city,
            String state,
            String zip,
            String phone,
            String payeeAccount,
            String amount,
            String fromAccount) {

        wait.until(ExpectedConditions.elementToBeClickable(billpaylink)).click();

        wait.until(ExpectedConditions.visibilityOf(payeename)).sendKeys(name);
        payeestreet.sendKeys(street);
        payeecity.sendKeys(city);
        payeestate.sendKeys(state);
        payeezipcode.sendKeys(zip);
        payeephone.sendKeys(phone);
        payeeaccount.sendKeys(payeeAccount);
        verifyaccount.sendKeys(payeeAccount);

        WebElement amountInput =
                wait.until(ExpectedConditions.visibilityOf(billpayamount));
        amountInput.clear();
        amountInput.sendKeys(amount);

        new Select(billpayfromaccount).selectByValue(fromAccount);

        wait.until(ExpectedConditions.elementToBeClickable(sendpaymentbutton)).click();
    }

    public void findTransactionByAmount(String account, String amount) {
        wait.until(ExpectedConditions.elementToBeClickable(findtransactionslink)).click();

        wait.until(ExpectedConditions.visibilityOf(searchaccount));
        new Select(searchaccount).selectByValue(account);

        WebElement searchAmount =
                wait.until(ExpectedConditions.visibilityOf(amountfield));
        searchAmount.clear();
        searchAmount.sendKeys(amount);

        wait.until(ExpectedConditions.elementToBeClickable(findbyamountbutton)).click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutlink)).click();
    }
    private void selectAccountWhenLoaded(WebElement dropdown, String accountNumber) {
        String expectedAccount = accountNumber.trim();

        wait.until(ExpectedConditions.visibilityOf(dropdown));

        wait.until(driver ->
                new Select(dropdown).getOptions().stream()
                        .anyMatch(option ->
                                expectedAccount.equals(option.getAttribute("value"))));

        new Select(dropdown).selectByValue(expectedAccount);
    }
    
}