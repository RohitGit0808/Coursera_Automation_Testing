@filter
Feature: Filter courses and extract details

  Scenario: Filter beginner English courses and extract details
    Given the browser is open on the Coursera homepage
    When the user searches for "web development"
    And the user filters beginner level and English language
    Then the user extracts top results