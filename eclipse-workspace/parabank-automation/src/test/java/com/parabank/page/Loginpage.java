package com.parabank.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Loginpage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[@id='loginPanel']//input[@name='username']")
    WebElement usernamefield;

    @FindBy(xpath = "//div[@id='loginPanel']//input[@name='password']")
    WebElement passwordfield;

    @FindBy(xpath = "//div[@id='loginPanel']//input[@type='submit' and @value='Log In']")
    WebElement loginbutton;

    public Loginpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void setLoginValues(String username, String password) {
        WebElement usernameInput =
                wait.until(ExpectedConditions.visibilityOf(usernamefield));
        usernameInput.clear();
        usernameInput.sendKeys(username);

        WebElement passwordInput =
                wait.until(ExpectedConditions.visibilityOf(passwordfield));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginbutton)).click();
    }
}