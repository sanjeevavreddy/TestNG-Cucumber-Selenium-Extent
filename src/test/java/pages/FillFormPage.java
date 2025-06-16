package pages;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import utils.CommonFunctions;
import utils.Locators;

public class FillFormPage {

    WebDriver driver;
    Locators locators = Locators.getInstance();
    CommonFunctions commonFunctions = new CommonFunctions();

    public FillFormPage() {
        this.driver = DriverManager.getDriver();
    }

    public void userEnterFirstName(String userName) {
        commonFunctions.findElement(locators.getLocator("UserName")).sendKeys(userName);
    }

    public void userEnterName(String password) {
        commonFunctions.findElement(locators.getLocator("Password")).sendKeys(password);
    }

    public void userLaunchTheUrl() {
        driver.get("https://facebook.com/login");
    }
}
