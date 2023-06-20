package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends Page {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id= "input-firstname")
    private WebElement firstNameField;
    @FindBy(id= "input-lastname")
    private WebElement lastNameField;
    @FindBy(id= "input-email")
    private WebElement emailField;
    @FindBy(id= "input-password")
    private WebElement passField;
    @FindBy(css= "input[value='1'][name='agree']")
    private WebElement checkBoxPolicy;
    @FindBy(css= "button[type='submit']")
    private WebElement continueButton;

    public void fillInTheRegisterForm(String firstName,String lastName,String email,String password){
        firstNameField.sendKeys(firstName);
        System.out.println("The entered first name is: " + firstName);
        lastNameField.sendKeys(lastName);
        System.out.println("The entered last name is: " + lastName);
        emailField.sendKeys(email);
        System.out.println("The entered email is: " + email);
        passField.sendKeys(password);
        System.out.println("The entered password is: " + password);
    }

    public void switchOnThePrivacyCheckBox(){
        checkBoxPolicy.click();
    }

    public void clickOnContinueButton(){
        continueButton.click();
    }

}