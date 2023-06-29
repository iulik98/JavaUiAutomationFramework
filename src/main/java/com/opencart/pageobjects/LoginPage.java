package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Page{

    @FindBy(css = "#input-email")
    private WebElement emailAddress;

    @FindBy(css = "#input-password")
    private WebElement passwordLogIn;

    @FindBy(css = "button[type='submit']")
    private WebElement logInButton;

    @FindBy(linkText = "Forgotten Password")
    private WebElement forgotPassLink;
    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void fillTheLoginForm(String email, String pass){
        emailAddress.sendKeys(email);
        System.out.println("Entered the following email: " + email);
        passwordLogIn.sendKeys(pass);
        System.out.println("Entered the following password: " + pass);
    }

    public void clickOnLogInButton(){
        logInButton.click();
        System.out.println("The login button was clicked.");
    }



}
