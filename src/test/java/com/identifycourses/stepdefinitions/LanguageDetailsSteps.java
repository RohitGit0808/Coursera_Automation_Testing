package com.identifycourses.stepdefinitions;

import com.identifycourses.pages.HomePage;
import com.identifycourses.pages.LanguageLearningPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class LanguageDetailsSteps {

    HomePage homePage = new HomePage();
    LanguageLearningPage languageLearningPage = new LanguageLearningPage();

    @And("the user navigates to language learning")
    public void the_user_navigates_to_language_learning() {
        homePage.navigateToLanguageLearning();
    }

    @Then("the user explores popular topics and fetches language data")
    public void the_user_explores_popular_topics_and_fetches_language_data() {
        languageLearningPage.clickOnFirstPopularTopic();
        languageLearningPage.fetchAllLanguagesAndCount();
        languageLearningPage.fetchLanguageLevelsAndCount();
    }
} 