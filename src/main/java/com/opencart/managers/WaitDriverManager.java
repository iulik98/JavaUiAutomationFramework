package com.opencart.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitDriverManager {
    private static WebDriver driver = DriverManager.getInstance().getDriver();
    private static final int explicitWaitValue = Integer.parseInt(ConfigReaderManager.getProperty("explicitWaitValue"));
    private static WebDriverWait wait = new WebDriverWait(DriverManager.getInstance().getDriver(), Duration.ofSeconds(explicitWaitValue));

    public static void getTheWebElementIfVisible(String xpath){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    public static WebElement getTheWebElementIfClickable(String xpath){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }
}
