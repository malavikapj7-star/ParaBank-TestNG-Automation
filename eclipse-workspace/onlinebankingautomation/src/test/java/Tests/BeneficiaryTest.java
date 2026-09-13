package Tests;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Base.BaseTest;
import Page.Loginpage;
import utilities.ConfigReader;

public class BeneficiaryTest extends BaseTest {

    @Test
    public void inspectBeneficiaryPage() {

        Loginpage loginPage = new Loginpage(driver);

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        System.out.println("========== AFTER LOGIN ==========");
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("TITLE: " + driver.getTitle());

        System.out.println("========== PAGE TEXT ==========");

        System.out.println(
                driver.findElement(By.tagName("body")).getText()
        );

        System.out.println("========== LINKS ==========");

        driver.findElements(By.tagName("a")).forEach(link -> {

            String text = link.getText().trim();

            if (!text.isEmpty()) {
                System.out.println(
                        "TEXT: [" + text + "] | HREF: ["
                        + link.getAttribute("href") + "]"
                );
            }
        });

        System.out.println("================================");
    }
}