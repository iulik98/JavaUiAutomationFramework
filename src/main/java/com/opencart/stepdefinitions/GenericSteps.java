package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.managers.ScrollManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GenericSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();

    @Given("The {string} is accessed")
    public void theIsAccessed(String urlValue) {
        driver.get(urlValue);
        System.out.println("The " + urlValue + " was accessed by the driver!");
    }

    @Then("the following error messages are displayed:")
    public void theFollowingErrorMessagesAreDisplayed(List<String> listOfMessages) {
        for (int i = 0; i < listOfMessages.size(); i++) {
            WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(),'" + listOfMessages.get(i) + "')]"));
            boolean isErrorMessageDisplayed = errorMessage.isDisplayed();
            Assertions.assertTrue(isErrorMessageDisplayed, "The error message: " + listOfMessages.get(i) + " is not displayed");

        }
    }

    @Then("the current URL contains the following keyword: {string}")
    public void theCurrentURLContainsTheFollowingKeyword(String keyword) {
        boolean urlContainsSuccessKeyword = driver.getCurrentUrl().contains(keyword);
        Assertions.assertTrue(urlContainsSuccessKeyword, "The URL does not contain the " + keyword + " keyword.");
    }

    @When("{string} from {string} is clicked")
    public void fromIsClicked(String elementName, String containingPage) {
        try {
            Class classInstance = Class.forName("com.opencart.pageobjects." + containingPage);
            Field webElementField = classInstance.getDeclaredField(elementName);
            webElementField.setAccessible(true);
            WebElement webElementToBeClicked = (WebElement) webElementField
                    .get(classInstance.getConstructor(WebDriver.class)
                            .newInstance(driver));
            ScrollManager.scrollToElement(webElementToBeClicked);
            ScrollManager.clickElement(webElementToBeClicked);
            Thread.sleep(500);
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
