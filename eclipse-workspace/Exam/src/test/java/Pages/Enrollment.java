package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Enrollment {

    WebDriver driver;

    // Example form locators
    By firstName = By.id("firstName");
    By lastName = By.id("lastName");
    By email = By.id("email");
    By phone = By.id("phone");

    public Enrollment(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String name) {

        driver.findElement(firstName).sendKeys(name);
    }

    public void enterLastName(String name) {

        driver.findElement(lastName).sendKeys(name);
    }

    public void enterEmail(String mail) {

        driver.findElement(email).sendKeys(mail);
    }

    public void enterPhone(String number) {

        driver.findElement(phone).sendKeys(number);
    }

    public void fillForm(
            String fname,
            String lname,
            String mail,
            String mobile) {

        enterFirstName(fname);
        enterLastName(lname);
        enterEmail(mail);
        enterPhone(mobile);
    }
}