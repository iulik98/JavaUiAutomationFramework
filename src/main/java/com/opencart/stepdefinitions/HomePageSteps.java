package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.AdminPage;
import com.opencart.pageobjects.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class HomePageSteps {
    WebDriver driver = DriverManager.getInstance().getDriver();
    HomePage homePage = new HomePage(driver);
    private static final Logger logger = LogManager.getLogger(HomePageSteps.class);

    @Given("HomePage is accessed")
    public void homepageIsAccessed() {
        driver.get("https://andreisecuqa.host/");
        logger.info("Home Page is accessed");
    }

    @And("Register page is accessed from home page menu")
    public void registerPageIsAccessedFromHomePageMenu() {
        homePage.navigateToRegisterPageFromHeaderMenu();
    }
}
