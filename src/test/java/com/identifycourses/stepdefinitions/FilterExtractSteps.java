package com.identifycourses.stepdefinitions;

import com.identifycourses.pages.SearchPage;
import io.cucumber.java.en.*;

public class FilterExtractSteps {

    SearchPage searchPage = new SearchPage();

    @And("the user filters beginner level and English language")
    public void the_user_filters_beginner_level_and_english_language() {
        searchPage.filterCourseLanguage();
//        searchPage.filterBeginnerLevel();
    }

    @Then("the user extracts top results")
    public void the_user_extracts_top_results() throws InterruptedException {
        searchPage.extractCourseDetails();
    }
} 