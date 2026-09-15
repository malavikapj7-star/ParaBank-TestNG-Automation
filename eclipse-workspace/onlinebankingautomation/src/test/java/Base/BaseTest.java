package Base;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utilities.ConfigReader;
import utilities.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

    	ConfigReader.loadProperties();

        String browser = ConfigReader.getProperty("browser");
        String url = ConfigReader.getProperty("url");

        DriverFactory.initializeDriver(browser);

        driver = DriverFactory.getDriver();

        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();
    }
}