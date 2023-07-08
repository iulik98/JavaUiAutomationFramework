package com.opencart.pageobjects;

import com.opencart.managers.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    protected final Logger logger = LogManager.getLogger(this.getClass());


    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void navigateToRegisterPageFromHeaderMenu() {
        accountIcon.click();
        logger.info("The account icon was clicked");

        registerBtn.click();
        logger.info("The register button was clicked");

    }

    public void clickOnLogOutOption(){
        accountIcon.click();
        logger.info("Account icon was clicked from logout method");
        logOutOption.click();
        logger.info("Logout button was clicked");
    }

    public void navigateToLoginPageFromHeaderMenu(){
        loginBtn.click();
        logger.info("The login option was clicked");
    }

    public boolean isUserLoggedIn(WebDriver driver){
        accountIcon.click();
        List<WebElement> listOfDropDowns = driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']/li"));
        return listOfDropDowns!=null && listOfDropDowns.size() > 2;
    }

}
