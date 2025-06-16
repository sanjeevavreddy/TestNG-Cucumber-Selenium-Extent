package stepdefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.BasePage;
import utils.RetryTracker;


public class FillFormDef extends BasePage {

    @Given("user launch the url")
    public void userLaunchTheUrl() {
        fillFormPage().userLaunchTheUrl();
    }

    @Then("user enter UserName and Password as {string} and {string}")
    public void userEnterFirstAndLastNameAsAnd(String userName, String password) {
        fillFormPage().userEnterFirstName(userName);
        fillFormPage().userEnterName(password);
    }

    @When("user click on submit button")
    public void user_click_on_submit_button() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("user should see the success message")
    public void user_should_see_the_success_message() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("user fail the scenario in the middle")
    public void userFailTheScenarioInTheMiddle() {
        Assert.fail("Failed");
    }
}
