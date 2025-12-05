package hooks;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import driver.DriverManager;
import io.cucumber.java.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.Reporter;
import utils.ExtentReportManager;
import utils.ExtentTestManager;

public class Hooks {
    private static final ExtentReports extent = ExtentReportManager.getExtentReports();
    private String currentStepText;

    @Before
    public void initiateDriver(Scenario scenario) {
        String testName = Reporter.getCurrentTestResult().getTestContext().getName();
        ExtentTestManager.startTest(testName, scenario.getName());
        DriverManager.initiateDriver();

    }

    @After(order = 2)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            attachScreenshot("Scenario failed");
        }
        ExtentTestManager.removeTest();
    }

    private void attachScreenshot(String msg) {
        try {
            String base64 = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
            ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64, msg);
        } catch (Exception e) {
            ExtentTestManager.getTest().fail("Screenshot failed: " + e.getMessage());
        }
    }

    @After(order = 1)
    public void quitDriver() {
        DriverManager.quitDriver();
    }

    @AfterAll
    public static void afterAll() {
        extent.flush();
    }

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        // Extract the current step using internal details
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement el : stackTrace) {
            if (el.getMethodName().startsWith("lambda$")) {
                currentStepText = el.getMethodName(); // fallback
                break;
            }
        }
    }
}
