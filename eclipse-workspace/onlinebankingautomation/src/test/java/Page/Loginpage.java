package Page;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Loginpage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("input[value='Log In']");

    private By errorMessage =
            By.cssSelector("#rightPanel .error");

    private By internalErrorMessage =
            By.xpath("//*[contains(text(),'An internal error has occurred')]");
    
    private By accountsOverviewLink =
            By.linkText("Accounts Overview");

    public Loginpage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );
    }

    public void enterUsername(String username) {

        WebElement usernameElement =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                usernameField
                        )
                );

        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    public void enterPassword(String password) {

        WebElement passwordElement =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                passwordField
                        )
                );

        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLogin() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        loginButton
                )
        ).click();
    }

    public void login(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLogin();

        wait.until(driver ->
                driver.getCurrentUrl().contains("overview.htm")
                        || !driver.findElements(errorMessage).isEmpty()
        );

        if (!isLoginSuccessful()) {

            String message = "Login failed. Current URL: "
                    + driver.getCurrentUrl();

            if (!driver.findElements(errorMessage).isEmpty()) {
                message += " | Error message: "
                        + getErrorMessage();
            }

            throw new RuntimeException(message);
        }
    }

    public boolean isLoginSuccessful() {

        return driver.getCurrentUrl().contains("overview.htm")
                &&
                !driver.findElements(accountsOverviewLink).isEmpty();
    }

    public boolean isLoginErrorDisplayed() {

        try {

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            errorMessage
                    )
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }
    public boolean isInternalServerErrorDisplayed() {
        return !driver.findElements(internalErrorMessage).isEmpty();
    }
    public String getErrorMessage() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        errorMessage
                )
        ).getText();
    }
}