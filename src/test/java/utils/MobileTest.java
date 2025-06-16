package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;

public class MobileTest {
    public static void main(String[] args) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        URL url = null;
        try {
            url = new URL("http://0.0.0.0:4723/wd/hub");
        } catch (MalformedURLException e) {
            Assert.fail(e.toString());
        }
        AndroidDriver driver = new AndroidDriver(url, options);

        WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Play Store']"));

        Actions action = new Actions(driver);
        action.moveToElement(ele);
        action.click().perform();

        driver.quit();
    }

    @SuppressWarnings("unused")
    private static void takeScreenShot(AndroidDriver driver) {

        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        File folder = new File(System.getProperty("user.dir") + "/target/Screenshots");
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("Folder created: " + folder.getAbsolutePath());
            } else {
                System.out.println("Failed to create folder.");
            }
        } else {
            System.out.println("Folder already exists: " + folder.getAbsolutePath());
        }

        File destFile = new File(System.getProperty("user.dir") + "/target/Screenshots/" + "screenshot.png");
        try {
            FileHandler.copy(srcFile, destFile);
        } catch (IOException e) {
            Assert.fail(e.toString());
        }
        System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
    }
}