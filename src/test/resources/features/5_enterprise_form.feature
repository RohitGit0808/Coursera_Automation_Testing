@enterprise_form
Feature: Interact with Coursera Enterprise

  Scenario: Navigate to enterprise and fill the form from the homepage
    Given the browser is open on the Coursera homepage
    When the user navigates to enterprise campus product page
    Then the user fills the form with test data
