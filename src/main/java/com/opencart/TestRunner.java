package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.FakeDataManager;
import com.opencart.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = DriverManager.getInstance().getDriver();
        driver.manage().window().maximize();

        driver.get("https://andreisecuqa.host/");

        HomePage homePage = new HomePage(driver);
        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = FakeDataManager.getRandomName();
        String lastName = FakeDataManager.getRandomName();
        String email = FakeDataManager.getRandomEmail();
        String password = FakeDataManager.getRandomPassword(4, 20);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillInTheRegisterForm(firstName, lastName, email, password);
        registerPage.switchOnThePrivacyCheckBox(driver);
        registerPage.clickOnContinueButton(driver);
        Thread.sleep(3000);

        CreatedAccountPage createdPage = new CreatedAccountPage(driver);
        if (createdPage.registrationConfirmed()) {
            System.out.println("Register action has finished successfully with the following text: " + createdPage.getConfirmationText());
        }

        createdPage.clickOnContinueButton();
        Thread.sleep(1000);

        homePage.clickOnLogOutOption();
        Thread.sleep(2000);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPageFromHeaderMenu();
        Thread.sleep(1000);
        loginPage.fillTheLoginForm(email, password);
        loginPage.clickOnLogInButton();

        Thread.sleep(2000);
        if (loginPage.isUserLoggedIn(driver)) {
            System.out.println("The login operation finished successfully.");
        } else System.out.println("There are some problems while performing login");

        driver.switchTo().newWindow(WindowType.TAB);

        AdminLoginPage admLogPage = new AdminLoginPage(driver);
        admLogPage.navigateToAdminPage(driver);
        admLogPage.fillInTheLoginForm("admin", "admin");
        admLogPage.clickOnTheButtonLogin();
        Thread.sleep(2000);

        AdminPage admPage = new AdminPage(driver);
        if (admPage.isUserPresentInTheDB(driver, email)) {
            System.out.println("The user with email: " + email + " is present in the DB");
        } else System.out.println("No records found in the DB with this email: " + email);

        driver.quit();

        System.out.println("Driver finished its work.");

    }

}