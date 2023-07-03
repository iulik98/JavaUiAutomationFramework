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



}
