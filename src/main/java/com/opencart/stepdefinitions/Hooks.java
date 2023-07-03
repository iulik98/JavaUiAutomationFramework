package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hooks {
    static int counter = 0;
    @BeforeAll
    public static void beforeAllTheTestsAreExecuted(){
        System.out.println("The execution starts");
    }
    @AfterAll
    public static void afterAllTheTestsAreExecuted(){
        System.out.println("The execution of all tests is terminated.");
    }

    @Before
    public void beforeEachTest(){
        counter++;
        System.out.println("The test number: " + counter + " started");
    }

    @After
    public void afterEachTest(){
        DriverManager.getInstance().quitWebDriver();
        System.out.println("The test with number:" + counter + " execution finished");
    }
}
