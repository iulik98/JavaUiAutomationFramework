package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.managers.FakeDataManager;
import com.opencart.pageobjects.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class RegisterPageSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    RegisterPage registerPage = new RegisterPage(driver);

    @When("the registration form is completed with valid random data")
    public void theRegistrationFormIsCompletedWithValidRandomData() {
        String firstName = FakeDataManager.getRandomName();
        String lastName = FakeDataManager.getRandomName();
        String email = FakeDataManager.getRandomEmail();
        String password = FakeDataManager.getRandomPassword(4, 20);
        registerPage.fillInTheRegisterForm(firstName, lastName, email, password);
    }

    @And("the privacy checkbox is enabled")
    public void thePrivacyCheckboxIsEnabled() {
        registerPage.switchOnThePrivacyCheckBox(driver);
    }

    @And("continueButton is clicked")
    public void continueButtonIsClicked() throws InterruptedException {
        registerPage.clickOnContinueButton(driver);
        Thread.sleep(500);
    }

    @And("the registration form is completed with the following data:")
    public void theRegistrationFormIsCompletedWithTheFollowingData(Map<String, String> userDetailsMap) {
        String firstNameValue = userDetailsMap.get("firstName");
        String lastNameValue = userDetailsMap.get("lastName");
        String emailValue = userDetailsMap.get("email");
        String passValue = userDetailsMap.get("password");

        if (firstNameValue!=null && firstNameValue.equalsIgnoreCase("random")) {
            firstNameValue = FakeDataManager.getRandomName();
        }
        if (lastNameValue!=null && lastNameValue.equalsIgnoreCase("random")) {
            lastNameValue = FakeDataManager.getRandomName();
        }
        if (emailValue!=null && emailValue.equalsIgnoreCase("random")) {
            emailValue = FakeDataManager.getRandomEmail();
        }
        if (passValue!=null && passValue.equalsIgnoreCase("random")) {
            passValue = FakeDataManager.getRandomPassword(4, 20);
        }
        registerPage.fillInTheRegisterForm(firstNameValue, lastNameValue, emailValue, passValue);
    }
}
