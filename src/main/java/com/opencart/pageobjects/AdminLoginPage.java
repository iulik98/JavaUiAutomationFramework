package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPage {
    private static final String PAGE_URL = "https://andreisecuqa.host/opencartadmin/";

    @FindBy(css = "#input-username")
    private WebElement userName;
    @FindBy(css = "#input-password")
    private WebElement pass;

    @FindBy(css = ".fa-solid.fa-key")
    private WebElement logBtn;

    public AdminLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void navigateToAdminPage(WebDriver driver){
        driver.get(PAGE_URL);
    }

    public void fillInTheLoginForm(String username, String password){
        userName.sendKeys(username);
        pass.sendKeys(password);
    }

    public void clickOnTheButtonLogin(){
        logBtn.click();
    }

}
