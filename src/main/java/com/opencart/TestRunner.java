package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.FakeDataManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.util.List;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        // Declare a WebDriver object that will be used for future actions.
        WebDriver driver = DriverManager.getInstance().getDriver();
        //  driver.manage().window().maximize();

//        DriverManager driverManager = DriverManager.getInstance();
//        WebDriver driver1 = driverManager.getDriver();

        driver.get("https://andreisecuqa.host/");

        String currentWindowName = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://andreisecuqa.host/");

        WebElement accountIcon = driver.findElement(By.xpath("//i[@class='fa-solid fa-user']"));
        accountIcon.click();

        WebElement registerButton = driver.findElement(By.linkText("Register"));
        registerButton.click();

        Thread.sleep(2);
        String firstName = FakeDataManager.getRandomName();
        WebElement firstNameField = driver.findElement(By.id("input-firstname"));
        firstNameField.sendKeys(firstName);
        System.out.println("Name: " + firstName);

        String lastName = FakeDataManager.getRandomName();
        System.out.println("LastName: " + lastName);
        WebElement lastNameField = driver.findElement(By.id("input-lastname"));
        lastNameField.sendKeys(lastName);

        String email = FakeDataManager.getRandomEmail();
        System.out.println("Email: " + email);
        WebElement emailField = driver.findElement(By.id("input-email"));
        emailField.sendKeys(email);

        String password = FakeDataManager.getRandomPassword(4, 20);
        System.out.println("password: " + password);
        WebElement passField = driver.findElement(By.id("input-password"));
        passField.sendKeys(password);

        WebElement checkBoxPolicy = driver.findElement(By.cssSelector("input[value='1'][name='agree']"));
        checkBoxPolicy.click();

        WebElement continueButton = driver.findElement(By.cssSelector("button[type='submit']"));
        continueButton.click();

        Thread.sleep(3000);

        String successRegisterText = "Congratulations! Your new account has been successfully created!";
        String text = driver.findElement(By.xpath("//div[@id='content']/p[1]")).getText();
        if (successRegisterText.equals(text)) {
            System.out.println("Register action has finished successfully with the following text: " + text);
        }

        driver.close();

        driver.switchTo().window(currentWindowName);
        Thread.sleep(2000);
        driver.get("https://andreisecuqa.host/");
        driver.findElement(By.cssSelector(".fa-solid.fa-user")).click();
        WebElement logoutButton = driver.findElement(By.linkText("Logout"));
        logoutButton.click();
        Thread.sleep(2000);
        WebElement loginButton = driver.findElement(By.linkText("Login"));
        loginButton.click();
        WebElement emailLogin = driver.findElement(By.xpath("//form[@id='form-login']//input[@id='input-email']"));
        emailLogin.sendKeys(email);
        WebElement passFieldLogin = driver.findElement(By.xpath("//form[@id='form-login']//input[@id='input-password']"));
        passFieldLogin.sendKeys(password);
        WebElement loginButtonLoggin = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButtonLoggin.click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".fa-solid.fa-user")).click();
        List<WebElement> listOfDropDowns = driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']/li"));
        if (listOfDropDowns!=null && listOfDropDowns.size() > 2) {
            System.out.println("The login operation finished successfully.");
        } else System.out.println("There are some problems while performing login");

        driver.quit();

        System.out.println("Driver finished its work.");


    }
}