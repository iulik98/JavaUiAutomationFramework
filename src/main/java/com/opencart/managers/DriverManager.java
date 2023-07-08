package com.opencart.managers;

import dev.failsafe.internal.util.Durations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Objects;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final String webDriverType = ConfigReaderManager.getProperty("browserType");
    private static DriverManager instance;
    private WebDriver driver;
    private final String optionsChrome = ConfigReaderManager.getProperty("options");

    private DriverManager() {
        switch (webDriverType.toUpperCase()) {
            case "CHROME":
                ChromeOptions options = new ChromeOptions();
                options.addArguments(optionsChrome.trim().split(",")[0]);
                options.addArguments(optionsChrome.trim().split(",")[1]);
                driver = new ChromeDriver(options);
                logger.info("Chrome driver was initialized");
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                logger.info("FireFox driver was initialized");
                break;
            case "EDGE":
                driver = new EdgeDriver();
                logger.info("Edge driver was initialized");
                break;
            case "SAFARI":
                driver = new SafariDriver();
                logger.info("Safari driver was initialized");
                break;
            default:
                logger.info("There is no such driver: " + webDriverType);
        }
        int implicitWaitValue = Integer.parseInt(ConfigReaderManager.getProperty("implicitWaitValue"));
        int implicitPageLoadValue = Integer.parseInt(ConfigReaderManager.getProperty("implicitPageLoadValue"));
        Objects.requireNonNull(driver).manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitValue));
        Objects.requireNonNull(driver).manage().timeouts().pageLoadTimeout(Duration.ofSeconds(implicitPageLoadValue));
    }

    public static DriverManager getInstance() {
        if (instance==null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driver==null) {
            getInstance();
        }
        return driver;
    }

    public void quitWebDriver() {
        driver.quit();
        driver = null;
        instance = null;
        logger.info("The browser is closed and session is set to null");
    }


}
