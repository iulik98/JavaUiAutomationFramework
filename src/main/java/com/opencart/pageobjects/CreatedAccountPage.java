package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatedAccountPage extends Page {

    @FindBy(css = ".btn.btn-primary")
    private WebElement continueButton;

    @FindBy(xpath = "//p[contains(text(),'Congratulations! Your new account has been success')]")
    private WebElement registrationConf;

    public CreatedAccountPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnContinueButton() {
        continueButton.click();
        logger.info("The continue button from CreatedAccountPage was clicked");
    }

    public String getConfirmationText() {
        return registrationConf.getText();
    }

    public boolean registrationConfirmed() {
        String successRegisterText = "Congratulations! Your new account has been successfully created!";
        String text = getConfirmationText();
        return successRegisterText.equals(text);
    }


}
