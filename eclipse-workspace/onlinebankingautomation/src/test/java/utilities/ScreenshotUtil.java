package utilities;



import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenshotUtil {

    public static String takeScreenshot(
            WebDriver driver,
            String testName) {

        try {

            File source =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            File folder =
                    new File("screenshots");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            String filePath =
                    "screenshots/" + testName + ".png";

            File destination =
                    new File(filePath);

            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            return destination.getAbsolutePath();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}