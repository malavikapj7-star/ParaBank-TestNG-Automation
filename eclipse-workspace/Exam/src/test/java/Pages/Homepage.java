package Pages;

import java.net.HttpURLConnection;
import java.time.Duration;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {

    WebDriver driver;

    // Locators
 // Locators

    By allLinks = By.tagName("a");

    By demoSite = By.xpath(
    	    "//a[contains(translate(normalize-space(.),"
    	    + "'abcdefghijklmnopqrstuvwxyz',"
    	    + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),"
    	    + "'DEMO SITE')]"
    	);

    By enrollNow = By.xpath(
        "//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'enroll now')]"
        + "|//button[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'enroll now')]"
    );

    public Homepage(WebDriver driver) {
        this.driver = driver;
    }

    // Find total number of links
    public int getTotalLinks() {

        List<WebElement> links = driver.findElements(allLinks);

        return links.size();
    }

    // Find broken links
    public void findBrokenLinks() {
    	 List<WebElement> links = driver.findElements(By.tagName("a"));

    	    List<String> urls = new ArrayList<>();

    	    // Get all href values first
    	    for (WebElement link : links) {

    	        try {
    	            String href = link.getAttribute("href");

    	            if (href != null && !href.isEmpty()) {
    	                urls.add(href);
    	            }

    	        } catch (Exception e) {
    	            System.out.println("Unable to get href from link");
    	        }
    	    }

    	    System.out.println("=================================");
    	    System.out.println("Checking Broken Links");
    	    System.out.println("=================================");

    	    int brokenLinks = 0;

    	    for (String href : urls) {

    	        try {

    	            URI uri = URI.create(href);
    	            URL url = uri.toURL();

    	            HttpURLConnection connection =
    	                    (HttpURLConnection) url.openConnection();

    	            connection.setRequestMethod("HEAD");
    	            connection.setConnectTimeout(3000);
    	            connection.setReadTimeout(3000);

    	            connection.connect();

    	            int responseCode = connection.getResponseCode();

    	            if (responseCode >= 400) {

    	                brokenLinks++;

    	                System.out.println(
    	                        "BROKEN : " + href +
    	                        " | Status Code : " + responseCode
    	                );

    	            } else {

    	                System.out.println(
    	                        "VALID  : " + href +
    	                        " | Status Code : " + responseCode
    	                );
    	            }

    	            connection.disconnect();

    	        } catch (Exception e) {

    	            brokenLinks++;

    	            System.out.println(
    	                    "BROKEN : " + href
    	            );
    	        }
    	    }

    	    System.out.println("=================================");
    	    System.out.println("Total Links  : " + links.size());
    	    System.out.println("Broken Links : " + brokenLinks);
    	    System.out.println("=================================");
    	}
        

       
    // Click DEMO SITE
    public void clickDemoSite() {

        String parentWindow = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement demoSiteElement = wait.until(
        	    ExpectedConditions.elementToBeClickable(demoSite)
        	);
        demoSiteElement.click();

        // Wait for new tab/window
        wait.until(driver -> driver.getWindowHandles().size() > 1);

        // Switch to child window
        for (String window : driver.getWindowHandles()) {

            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        System.out.println("Demo Site Title : " + driver.getTitle());
        System.out.println("Demo Site URL   : " + driver.getCurrentUrl());
    }
    // Click Enroll Now
    public void clickEnrollNow() {

        driver.findElement(enrollNow).click();
    }
}