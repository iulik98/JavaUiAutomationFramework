package com.opencart.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class Page {
    @FindBy(xpath = "//i[@class='fa-solid fa-user']")
    protected WebElement accountIcon;
    @FindBy(linkText = "Register")
    protected WebElement registerBtn;
    @FindBy(linkText = "Logout")
    protected WebElement logOutOption;

    @FindBy(linkText = "Login")
    protected WebElement loginBtn;

    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void navigateToRegisterPageFromHeaderMenu() {
        accountIcon.click();
        System.out.println("The account icon was clicked");

        registerBtn.click();
        System.out.println("The register button was clicked");

    }

    public void clickOnLogOutOption(){
        accountIcon.click();
        System.out.println("Account icon was clicked from logout method");
        logOutOption.click();
        System.out.println("Logout button was clicked");
    }

    public void navigateToLoginPageFromHeaderMenu(){
        loginBtn.click();
        System.out.println("The login option was clicked");
    }

    public boolean isUserLoggedIn(WebDriver driver){
        accountIcon.click();
        List<WebElement> listOfDropDowns = driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']/li"));
        return listOfDropDowns!=null && listOfDropDowns.size() > 2;
    }

}
