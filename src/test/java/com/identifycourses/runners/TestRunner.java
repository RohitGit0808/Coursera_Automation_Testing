package com.identifycourses.runners;

//import org.junit.runner.RunWith;
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.identifycourses.stepdefinitions", "com.identifycourses.hooks"},
    plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber-reports/cucumber-report.json", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "rerun:target/rerun.txt"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
