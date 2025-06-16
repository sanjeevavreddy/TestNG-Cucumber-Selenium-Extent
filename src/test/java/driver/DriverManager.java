package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import utils.TestParameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
    public final static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initiateDriver() {
        if (TestParameters.getGridURL().isEmpty()) {
            if (TestParameters.getBrowser().equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
            } else if (TestParameters.getBrowser().equals("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
            } else {
                Assert.fail(TestParameters.getBrowser() + " provided is not valid");
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            if (TestParameters.getBrowser().equalsIgnoreCase("chrome")) {
                capabilities.setBrowserName("chrome");
            } else if (TestParameters.getBrowser().equalsIgnoreCase("firefox")) {
                capabilities.setBrowserName("firefox");
            } else {
                Assert.fail(TestParameters.getBrowser() + " provided is not valid");
            }
            URL gridUrl;
            try {
                gridUrl = new URL(TestParameters.getGridURL());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            driver.set(new RemoteWebDriver(gridUrl, capabilities));
        }
        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}
