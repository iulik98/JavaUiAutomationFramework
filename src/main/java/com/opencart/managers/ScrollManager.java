package com.opencart.managers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ScrollManager {


    public static void scrollToElement(WebElement element){

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);",element);

    }

    public static void clickElement(WebElement element){

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getInstance().getDriver();
        js.executeScript("arguments[0].click();",element);

    }

}
