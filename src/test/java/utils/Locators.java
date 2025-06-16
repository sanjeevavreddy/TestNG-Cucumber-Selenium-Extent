package utils;

import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Locators {

    private static Locators instance;
    private Properties p = new Properties();

    private Locators() {
        try {
            FileInputStream f = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/resources/locators.properties");
            p.load(f);
        } catch (IOException err) {
            Assert.fail(err + "");
        }
    }

    public static Locators getInstance() {
        if (instance == null) {
            instance = new Locators();
        }
        return instance;
    }

    public By getLocator(String locatorName) {
        String locator = (String) p.get(locatorName);
        By by = null;
        if (locator == null) {
            Assert.fail(locatorName + " is not available in the Locators File");
        }
        if (locator.startsWith("id=")) {
            by = By.id(locator.substring(3));
        } else if (locator.startsWith("name=")) {
            by = By.name(locator.substring(5));
        } else if (locator.startsWith("xpath=")) {
            by = By.xpath(locator.substring(5));
        } else {
            Assert.fail(locatorName + " Logic for the locator type is not handled");
        }
        return by;
    }
}
