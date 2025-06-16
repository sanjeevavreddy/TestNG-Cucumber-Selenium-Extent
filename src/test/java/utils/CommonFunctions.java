package utils;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonFunctions {
    private static CommonFunctions instance;
    WebDriver driver;

    public CommonFunctions() {
        this.driver = DriverManager.getDriver();
    }

    public WebElement findElement(By by) {
        WebElement element = driver.findElement(by);
        highLightElement(element);
        return element;
    }

    public WebElement findElement(By by, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        highLightElement(element);
        return element;
    }

    public void highLightElement(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false)", element);
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;')", element);
        try {
            Thread.sleep(500);
        } catch (Exception ignored) {
        }
        js.executeScript("arguments[0].setAttribute('style', 'background: ; border: ;')", element);
    }

}
