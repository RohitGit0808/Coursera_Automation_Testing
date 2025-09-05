package com.identifycourses.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "@target/rerun.txt",
    glue = {"com.identifycourses.stepdefinitions", "com.identifycourses.hooks"},
    plugin = {"pretty", "html:target/cucumber-rerun-reports.html", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
    monochrome = true
)
public class RerunTestRunner extends AbstractTestNGCucumberTests {
} 
