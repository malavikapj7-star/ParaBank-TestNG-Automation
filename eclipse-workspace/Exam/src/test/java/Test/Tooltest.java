package Test;


import org.testng.annotations.Test;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import Baseclass.Basejava;
import Pages.Demopage;
import Pages.Enrollment;
import Pages.Homepage;

public class Tooltest extends Basejava {
	

    @Test
    public void tooltest() throws Exception {
    
    	 {

    	        System.out.println("Test started");

    	        System.out.println(driver.getTitle());
    	 }
    	 
        // Create page objects
        Homepage home = new Homepage(driver);
        Demopage demo = new Demopage(driver);
        Enrollment enroll = new Enrollment(driver);

        // =========================================
        // 1 & 2. Open URL and find links
        // =========================================

        int totalLinks = home.getTotalLinks();

        System.out.println("Total number of links : " + totalLinks);

        home.findBrokenLinks();

        // =========================================
        // 3. Click DEMO SITE
        // =========================================

        demo.saveParentWindow();

        home.clickDemoSite();
       

        System.out.println("Demo Site Title : " + driver.getTitle());
        System.out.println("Demo Site URL   : " + driver.getCurrentUrl());

        // Wait for new tab
        Thread.sleep(2000);

        // =========================================
        // 4. Switch to new tab
        // =========================================

        demo.switchToNewTab();

        System.out.println(
            "New Tab Title : " + demo.getPageTitle()
        );

        System.out.println(
            "New Tab URL : " + demo.getPageURL()
        );

        // =========================================
        // 5. Verify title
        // =========================================

        Assert.assertTrue(
            demo.getPageTitle().contains("ToolsQA"),
            "Title verification failed"
        );

        // =========================================
        // 6. Verify URL
        // =========================================

        Assert.assertTrue(
            demo.getPageURL().contains("demoqa"),
            "URL verification failed"
        );

        // =========================================
        // 7. Switch back to parent
        // =========================================

        demo.switchBackToParent();

        // =========================================
        // 8. Click Enroll Now
        // =========================================

        home.clickEnrollNow();

        // =========================================
        // Fill form
        // =========================================

        enroll.fillForm(
            "Malavika",
            "Test",
            "malavika@test.com",
            "9876599800"
        );

        // =========================================
        // 9. Screenshot
        // =========================================

        takeScreenshot("ToolsQA_Enroll_Form");

        System.out.println("Test completed successfully");
    }

    // Screenshot method
    public void takeScreenshot(String fileName) throws Exception {

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        File destination = new File(
            System.getProperty("user.dir")
            + "/screenshots/"
            + fileName
            + ".png"
        );

        FileHandler.copy(source, destination);

        System.out.println(
            "Screenshot saved at: " + destination.getAbsolutePath()
        );
    
        File destinationFolder = destination.getParentFile();

        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        FileHandler.copy(source, destination);

        System.out.println(
            "Screenshot saved at : "
            + destination.getAbsolutePath()
        );
    }
}