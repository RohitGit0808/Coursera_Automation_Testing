

package com.identifycourses.pages;

import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.identifycourses.models.SearchResults;
import com.identifycourses.utils.CsvUtil;

public class SearchPage extends BasePage {
	
	private static final Logger logger = LogManager.getLogger(SearchPage.class);
	
	private JavascriptExecutor js;
	
//	@FindBy(xpath = "//*[text()='Beginner']")
	@FindBy(xpath = "//input[@id='cds-react-aria-:R8qlmpiel6dakqdqla:']")
	private WebElement courseLevel;
	
	
	@FindBy(xpath = "//*[contains(@data-testid, 'language:English')]")
	private WebElement courseLanguage;
	
	
	@FindBy(xpath = "//*[@data-testid='product-card-cds']")
	private List<WebElement> searchCards;
	
	@FindBy(xpath = "//*[text()='Explore']")
	private WebElement exploreBtn;
	
	@FindBy(linkText = "Language Learning")
	private WebElement languageLearning;
	
	public SearchPage() {
		super();
		this.js = (JavascriptExecutor) driver;
	}
	
	public void filterBeginnerLevel() {
		try {
			wait.until(ExpectedConditions.visibilityOf(courseLevel));
//			wait.until(ExpectedConditions.elementToBeClickable(courseLevel));
			String scrollScript = "arguments[0].scrollIntoView(true);";
			
			logger.info("Scrolling to beginner level element");
			
			js.executeScript(scrollScript, courseLevel);
			
			logger.info("Clicking beginner level element");
			courseLevel.click();
//			js.executeScript("arguments[0].click();", courseLevel);			
			logger.info("Clicked beginner level element");
		} catch(Exception e) {
			logger.error("Error occured while scrolling and clicking beginner level", e);
		}
	}
	
	public void filterCourseLanguage() {
		try {
			wait.until(ExpectedConditions.visibilityOf(courseLanguage));
//			wait.until(ExpectedConditions.elementToBeClickable(courseLanguage));
			String scrollScript = "arguments[0].scrollIntoView(true);";
			
			logger.info("Scrolling to English language element");
			
			js.executeScript(scrollScript, courseLanguage);
			
			logger.info("Clicking English language element");
			courseLanguage.click();
//			js.executeScript("arguments[0].click();", courseLanguage);
			
			logger.info("Clicked English language element");
		} catch(Exception e) {
			logger.error("Error occured while scrolling and clicking English language", e);
		}
	}
	
	public SearchResults extractCourseDetails() throws InterruptedException {
		SearchResults searchResults = null;
		List<SearchResults> resultsToWrite = new ArrayList<>();
//		Thread.sleep(3000);
		List<WebElement> searchCards1 = wait.until(ExpectedConditions.visibilityOfAllElements(searchCards));
		try {
			for(int i = 0; i < Math.min(2, searchCards1.size()); i++) {
//				WebElement card = searchCards.get(i);
				WebElement card = wait.until(ExpectedConditions.visibilityOf(searchCards.get(i)));
				
//				WebElement titleElement = card.findElement(By.cssSelector("h3"));
				WebElement titleElement = wait.until(ExpectedConditions.visibilityOf(card.findElement(By.cssSelector("h3"))));
	            String courseName = titleElement.getText();
	            
//	            WebElement ratingElement = card.findElement(By.cssSelector("[aria-label^='Rating'] span"));
//	            WebElement ratingElement = wait.until(ExpectedConditions.visibilityOf(card.findElement(By.cssSelector("span.css-6ecy9b"))));
//	            WebElement ratingElement = wait.until(ExpectedConditions.visibilityOf(card.findElement(By.cssSelector("[aria-label^='Rating'] span"))));
	            WebElement ratingElement = wait.until(ExpectedConditions.visibilityOf(card.findElement(By.xpath("//div[@aria-label='Rating']/child::span"))));
	            js.executeScript("arguments[0].scrollIntoView(true);", ratingElement);
	            String rating = ratingElement.getText();
	            
//	            WebElement durationElement = card.findElement(By.xpath(".//p[contains(text(),'Course')]"));
	            WebElement durationElement = wait.until(ExpectedConditions.visibilityOf(card.findElement(By.xpath("//p[contains(text(),'Course')]"))));
	            String duration = durationElement.getText().split("\\·")[2].trim();
	            
	            searchResults = new SearchResults(courseName, rating, duration);
	            resultsToWrite.add(searchResults);

	    
	            logger.info("Course Name - " + searchResults.getCourseName());
	            logger.info("Rating - " + searchResults.getRating());
	            logger.info("Duration - " + searchResults.getDuration());
			}
			CsvUtil.writeSearchResultsToCsv("src/test/resources/testdata/course_details.csv", resultsToWrite);
		} catch(Exception e) {
			logger.error("Error occurred while extracting course details", e);
		}
		return searchResults;
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

