package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.managers.FakeDataManager;
import com.opencart.pageobjects.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

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
        registerPage.switchOnThePrivacyCheckBox();
    }

    @And("continueButton is clicked")
    public void continueButtonIsClicked() {
        registerPage.clickOnContinueButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
