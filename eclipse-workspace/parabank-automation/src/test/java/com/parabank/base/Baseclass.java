package com.parabank.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.parabank.page.Bankingpage;
import com.parabank.page.Loginpage;

public class Baseclass {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static ExtentSparkReporter reporter;
    public static ExtentTest test;
    public static ExtentReports extent;

    public Loginpage lp;
    public Bankingpage bp;

    @BeforeTest
    public void setUp() {
        // Create the folder so the report can be saved there.
        new File("./Report").mkdirs();

        reporter = new ExtentSparkReporter("./Report/parabankreport.html");
        reporter.config().setDocumentTitle("ParaBank Automation Report");
        reporter.config().setReportName("ParaBank Test Report");
        reporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Browser", "Chrome");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // ParaBank login form XPath: loginPanel contains the username input.
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='loginPanel']//input[@name='username']")));

        lp = new Loginpage(driver);
        bp = new Bankingpage(driver);

        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());

            if (driver != null) {
                TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
                String screenshot =
                        screenshotDriver.getScreenshotAs(OutputType.BASE64);

                test.addScreenCaptureFromBase64String(
                        screenshot, "Failure Screenshot");
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Skipped");
        }
    }

    @AfterTest
    public void tearDown() {
        if (extent != null) {
            extent.flush();
        }

        if (driver != null) {
            driver.quit();
        }
    }
}