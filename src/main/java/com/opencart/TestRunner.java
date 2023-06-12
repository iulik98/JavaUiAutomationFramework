package com.opencart;

import com.opencart.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
        // Declare a WebDriver object that will be used for future actions.
        WebDriver driver = DriverManager.getInstance().getDriver();

//        DriverManager driverManager = DriverManager.getInstance();
//        WebDriver driver1 = driverManager.getDriver();

        driver.get("https://mvnrepository.com/");

        String currentWindowName= driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://mvnrepository.com/artifact/org.scalatra/scalatra");
        Thread.sleep(5000);

        driver.close();

        driver.switchTo().window(currentWindowName);

        driver.get("https://www.google.md/");

        driver.quit();

        System.out.println("The execution was finsihed");

    }
}