package com.opencart.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private static String webDriverType = "Chrome";
    private static DriverManager instance;
    private WebDriver driver;

    private DriverManager() {
        switch (webDriverType.toUpperCase()) {
            case "CHROME":
                driver = new ChromeDriver();
                System.out.println("Chrome driver was initialized");
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                System.out.println("FireFox driver was initialized");
                break;
            case "EDGE":
                driver = new EdgeDriver();
                System.out.println("Edge driver was initialized");
                break;
            case "SAFARI":
                driver = new SafariDriver();
                System.out.println("Safari driver was initialized");
                break;
            default:
                System.out.println("There is no such driver: " + webDriverType);
        }
    }

    public static DriverManager getInstance(){
        if (instance == null){
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver(){
        if (driver == null) {
            getInstance();
        }
        return driver;
    }


}