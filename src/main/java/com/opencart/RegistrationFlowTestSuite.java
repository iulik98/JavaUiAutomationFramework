package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.FakeDataManager;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class RegistrationFlowTestSuite {
    static WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    static int counter = 0;

    @BeforeAll
    public static void beforeAll(){
        System.out.println("The execution starts");
    }

    @BeforeEach
    public void beforeEach(){
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://andreisecuqa.host/");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        counter++;
        System.out.println("The test number: " + counter + " started");
    }

    @Test
    @DisplayName("The URL contains 'success' keyword after registration with valid data")
    public void registerFlowRedirectsTheUserToTheCorrectURL() throws InterruptedException {
     //   homePage.navigateToRegisterPageFromHeaderMenu();
        String firstName = FakeDataManager.getRandomName();
        String lastName = FakeDataManager.getRandomName();
        String email = FakeDataManager.getRandomEmail();
        String password = FakeDataManager.getRandomPassword(4, 20);

        RegisterPage registerPage = new RegisterPage(driver);
//        registerPage.fillInTheRegisterForm(firstName, lastName, email, password);
//        registerPage.switchOnThePrivacyCheckBox();
//        registerPage.clickOnContinueButton();
        Thread.sleep(3000);

        boolean urlContainsSuccessKeyword = driver.getCurrentUrl().contains("success");
        Assertions.assertTrue(urlContainsSuccessKeyword,"The URL does not contain the success keyword.");
    }

    @Test
    @DisplayName("The URL contains 'register' when privacy policy is not checked")
    public void registrationFlowBlockedIfPrivacyCheckBoxNotSelected() throws InterruptedException {
     //   homePage.navigateToRegisterPageFromHeaderMenu();
        String firstName = FakeDataManager.getRandomName();
        String lastName = FakeDataManager.getRandomName();
        String email = FakeDataManager.getRandomEmail();
        String password = FakeDataManager.getRandomPassword(4, 20);

        RegisterPage registerPage = new RegisterPage(driver);
//        registerPage.fillInTheRegisterForm(firstName, lastName, email, password);
//        registerPage.clickOnContinueButton();
        Thread.sleep(3000);

        boolean urlContainsSuccessKeyword = driver.getCurrentUrl().contains("success");
        Assertions.assertFalse(urlContainsSuccessKeyword,"The URL contains the success keyword.");
        boolean urlContainsRegisterKeyword = driver.getCurrentUrl().contains("register");
        Assertions.assertTrue(urlContainsRegisterKeyword,"The URL does not contain the Register Keyword");

    }

    @AfterEach
    public void afterEachTestExecution(){
        DriverManager.getInstance().quitWebDriver();
        System.out.println("The test with number:" + counter + " execution finished");
    }

    @AfterAll
    public static void afterExecution(){
        System.out.println("The execution is terminated.");
    }

}
