package Pages;


import java.util.Set;

import org.openqa.selenium.WebDriver;

public class Demopage {

    WebDriver driver;

    String parentWindow;

    public Demopage(WebDriver driver) {
        this.driver = driver;
    }

    // Save parent window
    public void saveParentWindow() {

        parentWindow = driver.getWindowHandle();
    }

    // Switch to new tab
    public void switchToNewTab() {

        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {

            if (!window.equals(parentWindow)) {

                driver.switchTo().window(window);

                break;
            }
        }
    }

    // Get title
    public String getPageTitle() {

        return driver.getTitle();
    }

    // Get URL
    public String getPageURL() {

        return driver.getCurrentUrl();
    }

    // Switch back to parent
    public void switchBackToParent() {

        driver.switchTo().window(parentWindow);
    }
}