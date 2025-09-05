

package com.identifycourses.pages;


import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.identifycourses.models.LanguageDetails;
import com.identifycourses.models.LanguageLevels;
import com.identifycourses.utils.JsonUtil;


public class LanguageLearningPage extends BasePage {
	
	private static final Logger logger = LogManager.getLogger(LanguageLearningPage.class);
	
	private JavascriptExecutor js;
	private List<LanguageDetails> cachedLanguages = new ArrayList<>();
	private List<LanguageLevels> cachedLevels = new ArrayList<>();
	
	@FindBy(xpath = "//h2[text()='Core skills']//parent::div//following-sibling::div//div//a")
	private List<WebElement> popularTopics;
	
	@FindBy(xpath = "//div[@data-testid=\"search-filter-group-Language\"]//span[contains(text(), 'Show')]")
	private WebElement langaugesShowMore;
	
	@FindBy(xpath = "//div[@aria-label = 'Select Language options']//div[@class='cds-checkboxAndRadio-labelText']")
	private List<WebElement> allLanguages;
	
	@FindBy(xpath = "//button[@aria-label='Close' and @tabindex='0']")
	private WebElement languagePopCloseBtn;
	
	@FindBy(xpath = "//div[contains(@data-testid, 'productDifficultyLevel')]")
	private List<WebElement> allLanguagesLevels;
	
	public LanguageLearningPage() {
		super();
		this.js = (JavascriptExecutor) driver;
	}
	
	public void clickOnFirstPopularTopic() {
		try {
			String scrollScript = "arguments[0].scrollIntoView({block: 'center'});";
			logger.info("Scrolling to Popular topics element");
			js.executeScript(scrollScript, popularTopics.get(2));
			
			logger.info("Feteching popular topics");
			popularTopics.get(2).click();
			
		} catch(Exception e) {
			logger.error("Can't fetch popular topics", e);
		}
	}
	
	public LanguageDetails fetchAllLanguagesAndCount() {
		LanguageDetails languageDetails = null;
		
		try {
			String scrollScript = "arguments[0].scrollIntoView({block: 'center'});";
			logger.info("Scrolling to Languages element");
			js.executeScript(scrollScript, langaugesShowMore);
			
			Actions action = new Actions(driver);
			action.moveToElement(langaugesShowMore).click().perform();
			
			wait.until(ExpectedConditions.visibilityOfAllElements(allLanguages));
			
			for(WebElement langElement : allLanguages) {
				String text = langElement.getText(); 
				
				if (text != null && !text.isEmpty()) {
                    String language = text.replaceAll("[^a-zA-Z]+", "").trim(); // "English"
                    String countStr = text.replaceAll("[^0-9,]", ""); // "13,100"
                    int count = Integer.parseInt(countStr.replace(",", "")); // 13100
                    languageDetails = new LanguageDetails(language, count);
					cachedLanguages.add(languageDetails);
                }
				logger.info("Language - " + languageDetails.getLanguage());
				logger.info("Count - " + languageDetails.getCount());
			}
			languagePopCloseBtn.click();
			
			JsonUtil.writeLanguageDataToJson("src/test/resources/testdata/language_details.json", cachedLanguages, cachedLevels.isEmpty() ? null : cachedLevels);
			
		} catch(Exception e) {
			logger.error("Error in fetching all languages", e);
		}
		return languageDetails;
	}
	
	public LanguageLevels fetchLanguageLevelsAndCount() {
		LanguageLevels languageLevels = null;
		try {
			String scrollScript = "arguments[0].scrollIntoView({block: 'center'});";
			logger.info("Scrolling to Languages element");
			js.executeScript(scrollScript, allLanguagesLevels.get(0));
			
			for(WebElement langElement : allLanguagesLevels) {
				String text = langElement.getText(); 
				if (text != null && !text.isEmpty()) {
                    String languageLevel = text.replaceAll("[^a-zA-Z]+", "").trim(); 
                    String countStr = text.replaceAll("[^0-9,]", ""); 
                    int count = Integer.parseInt(countStr.replace(",", "")); 
                    languageLevels = new LanguageLevels(languageLevel, count);
					cachedLevels.add(languageLevels);
                }
				logger.info("Language Level - " + languageLevels.getLanguageLevel());
				logger.info("Count - " + languageLevels.getCount());
			}
			
			JsonUtil.writeLanguageDataToJson("src/test/resources/testdata/language_details.json", cachedLanguages.isEmpty() ? null : cachedLanguages, cachedLevels);
			
		} catch(Exception e) {
			logger.error("Error in fetching all languages levels", e);
		}
		return languageLevels;
	}
}