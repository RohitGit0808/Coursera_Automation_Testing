package com.identifycourses.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
	
	private static final Logger logger = LogManager.getLogger(HomePage.class);
	
	@FindBy(css = "input[placeholder='What do you want to learn?']")
	private WebElement searchBar;
	
	@FindBy(xpath = "//*[text()='Explore']")
	private WebElement exploreBtn;
	
	@FindBy(linkText = "Language Learning")
	private WebElement languageLearning;
	
	public HomePage() {
		super();
	}
	
	public void searchCourse(String course) {
		try {
			wait.until(ExpectedConditions.visibilityOf(searchBar));
			
			logger.info("Attempting search");
			searchBar.sendKeys(course + Keys.ENTER);
			
			logger.info("searching");
			 
		} catch(Exception e) {
			logger.error("No search bar appeared.");
		}
	}
	
	public void navigateToLanguageLearning() {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(exploreBtn).perform();
			
			wait.until(ExpectedConditions.visibilityOf(languageLearning)).click(); 
			
		} catch(Exception e) {
			logger.error("Error occurred while navigating to Language Learning Page", e);
		}
	}
	
}
