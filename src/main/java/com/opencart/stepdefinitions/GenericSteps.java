package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.managers.FakeDataManager;
import com.opencart.managers.ScrollManager;
import com.opencart.managers.WaitDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GenericSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    private static final Logger logger = LogManager.getLogger(GenericSteps.class);

    public WebElement configureReflectionAndReturnWebElement(String fieldName, Class<?> classInstance) {
        WebElement webElement = null;

        try {
            Field webElementField = classInstance.getDeclaredField(fieldName);
            webElementField.setAccessible(true);
            webElement = (WebElement) webElementField
                    .get(classInstance.getConstructor(WebDriver.class)
                            .newInstance(driver));
        } catch (NoSuchFieldException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return webElement;
    }

    @Given("The {string} is accessed")
    public void theIsAccessed(String urlValue) {
        driver.get(urlValue);
        logger.info("The " + urlValue + " was accessed by the driver!");
    }

    @Then("the following error messages are displayed:")
    public void theFollowingErrorMessagesAreDisplayed(List<String> listOfMessages) {
        for (String listOfMessage : listOfMessages) {
//            WaitDriverManager.getTheWebElementIfVisible("//*[contains(text(),'" + listOfMessage + "')]");
           // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + listOfMessage + "')]")));
            WebElement errorMessage = driver.findElement(By.xpath("//*[contains(text(),'" + listOfMessage + "')]"));
            boolean isErrorMessageDisplayed = errorMessage.isDisplayed();
            Assertions.assertTrue(isErrorMessageDisplayed, "The error message: " + listOfMessage + " is not displayed");

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
            Class<?> classInstance = Class.forName("com.opencart.pageobjects." + containingPage);
            WebElement webElementToBeClicked = configureReflectionAndReturnWebElement(elementName, classInstance);
            ScrollManager.scrollToElement(webElementToBeClicked);
            ScrollManager.clickElement(webElementToBeClicked);
            Thread.sleep(500);
        } catch (ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @And("the following fields from {string} are populated with data:")
    public void theFollowingFieldsFromArePopulatedWithData(String pageName, Map<String, String> fieldValueMap) {
        try {
            Class<?> classInstance = Class.forName("com.opencart.pageobjects." + pageName);
            for (Map.Entry<String, String> entry : fieldValueMap.entrySet()) {
                String valueToSet = "";
                if (entry.getValue().equalsIgnoreCase("random")) {
                    valueToSet = FakeDataManager.getRandomData(entry.getKey());
                }
                WebElement webElementToInsertIn = configureReflectionAndReturnWebElement(entry.getKey(), classInstance);
                ScrollManager.scrollToElement(webElementToInsertIn);
                webElementToInsertIn.sendKeys(!Objects.equals(valueToSet, "") ? valueToSet:entry.getValue());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}