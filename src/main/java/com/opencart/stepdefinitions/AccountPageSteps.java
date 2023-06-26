package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class AccountPageSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    @Then("the new page URL contains {string} keyword")
    public void theNewPageURLContainsKeyword(String desiredValue) {
        boolean urlContainsSuccessKeyword = driver.getCurrentUrl().contains(desiredValue);
        Assertions.assertTrue(urlContainsSuccessKeyword,"The URL does not contain the "+desiredValue+" keyword.");
    }
}
