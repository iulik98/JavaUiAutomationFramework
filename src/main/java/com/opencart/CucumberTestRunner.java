package com.opencart;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/java/com/opencart/features",
        glue = "com.opencart.stepdefinitions",
        tags = "@Smoke or @Regression",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class CucumberTestRunner {
}
