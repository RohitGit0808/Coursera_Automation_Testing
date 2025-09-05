@search
Feature: Search Courses on Coursera

  Scenario: Search a course from the homepage
    Given the browser is open on the Coursera homepage
    When the user searches for "web development"
