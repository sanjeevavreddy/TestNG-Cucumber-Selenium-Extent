package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.*;
import utils.RetryAnalyzer;
import utils.TestParameters;

import java.text.SimpleDateFormat;
import java.util.Date;


@CucumberOptions(
        features = "classpath:features",
        glue = {"stepdefinitions", "hooks"}
//        plugin = {
//                "pretty",
//                "html:target/cucumber-reports/report.html"
//        },
//        plugin = {
//                "pretty",
//                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // Key plugin
//        }
//        monochrome = true,
//        tags = ""
)
//@Listeners({AllureTestNg.class})
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeTest(alwaysRun = true)
    @Parameters({"browser", "gridURL"})
    public void setBrowserAndGrid(@Optional("chrome") String browser, @Optional("") String gridURL) {
        System.out.println("Setting the browser before Test:" + TestParameters.getBrowser());
        TestParameters.setBrowser(browser);
        TestParameters.setGridURL(gridURL);
        System.out.println("Setting the browser after Test:" + TestParameters.getBrowser());
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Test(dataProvider = "scenarios", retryAnalyzer = RetryAnalyzer.class)
    public void runScenario(PickleWrapper pickle, FeatureWrapper feature) {
        super.runScenario(pickle, feature);
    }
}