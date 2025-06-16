package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import utils.TestParameters;


@CucumberOptions(
        features = "src/test/java/resources/features",
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty", "html:target/cucumber-reports/cucumber-pretty/chrome-report.html"},
        monochrome = true,
        tags = ""
)
public class ChromeTestRunner extends AbstractTestNGCucumberTests {


    @BeforeMethod(alwaysRun = true)
    public void initiateDriver(@Optional("") String gridURL){
        TestParameters.setBrowser("chrome");
        TestParameters.setGridURL(gridURL);
        System.out.println("Setting the browser before method:" +TestParameters.getBrowser());
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}