package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    static int counter = 0;

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    @BeforeAll
    public static void beforeAllTheTestsAreExecuted(){
        logger.log(Level.INFO, "The execution starts");
    }
    @AfterAll
    public static void afterAllTheTestsAreExecuted(){
        logger.log(Level.INFO, "The execution of all tests is terminated.");
    }

    @Before
    public void beforeEachTest(){
        counter++;
        logger.info("The test number: " + counter + " started");
    }

    @After
    public void afterEachTest(){
        DriverManager.getInstance().quitWebDriver();
        logger.info("The test with number:" + counter + " execution finished");
    }
}
