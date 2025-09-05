package com.identifycourses.stepdefinitions;

import org.testng.Assert;

import com.identifycourses.pages.HomePage;
import io.cucumber.java.en.*;

public class SearchCoursesSteps {

    HomePage homePage = new HomePage();

    @Given("the browser is open on the Coursera homepage")
    public void the_browser_is_open_on_the_coursera_homepage() {
        // Browser is launched and navigated via Hooks
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String course) {
        homePage.searchCourse(course);
//        Assert.fail();
    }
}
