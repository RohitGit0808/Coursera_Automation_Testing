package com.identifycourses.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.identifycourses.models.FormData;

public class EnterprisePage extends BasePage {
	
	private static final Logger logger = LogManager.getLogger(EnterprisePage.class);
	
	private JavascriptExecutor js;
	
	@FindBy(className = "rc-CourseraLogo")
	private WebElement courseraLogo;
	
	@FindBy(xpath = "//h3[text()='Campus']")
	private WebElement campusElement;
	
	@FindBy(xpath = "//span[contains(text(), 'Learn more')]")
	private List<WebElement> learnMoreList;
	
	@FindBy(id = "FirstName")
	private WebElement fnameEle;
	
	@FindBy(id = "LastName")
	private WebElement lnameEle;
	
	@FindBy(id = "Email")
	private WebElement emailField;
	
	@FindBy(id = "Phone")
	private WebElement phoneField;
	
	@FindBy(id = "Institution_Type__c")
	private WebElement institutionDropdown;
	
	@FindBy(id = "Company")
	private WebElement institutionNameEle;
	
	@FindBy(id = "Title")
	private WebElement jobRoleDropdown;
	
	@FindBy(id = "Department")
	private WebElement departmentDropdown;
	
	@FindBy(id = "What_the_lead_asked_for_on_the_website__c")
	private WebElement needsDropdown;
	
	@FindBy(id = "Country")
	private WebElement countryDropdown;
	
	@FindBy(id = "State")
	private WebElement stateDropdown;
	
	@FindBy(className = "mktoButton")
	private WebElement submitBtn;
	
	@FindBy(xpath = "//div[@id = 'ValidMsgEmail' and @class = 'mktoErrorMsg']")
	private WebElement invalidEmail;
	
	
	public EnterprisePage() {
		super();
		this.js = (JavascriptExecutor) driver;
	}
	
	public void returnToHomePage() {
		try {
			logger.info("Navigating to home page");
			courseraLogo.click();
		} catch(Exception e) {
			logger.error("Unable to click the logo", e);
		}
	}
	
	public void navigateToForEnterprise() {
		try {
			String enterpriseUrl = driver.getCurrentUrl() + "enterprise";
			driver.navigate().to(enterpriseUrl);
		} catch(Exception e) {
			logger.error("Cannot navigate to Enterprise page", e);
		}
	}
	
	public void navigateToCampusProduct() {
		try {
			wait.until(ExpectedConditions.visibilityOf(campusElement));
			String scrollScript = "arguments[0].scrollIntoView({block: 'center'})";
			
			logger.info("Scrolling to Campus Product element");
			
			js.executeScript(scrollScript, campusElement);
			
			learnMoreList.get(1).click();
		} catch(Exception e) {
			logger.error("Cannot navigate to Campus Product Page", e);
		}
	}
	
	public void fillForm(FormData formData) {
		try {
			String scrollScript = "arguments[0].scrollIntoView({block: 'center'})";
			
			js.executeScript(scrollScript, emailField);
			
			logger.info("Filling form");

			fnameEle.sendKeys(formData.getFirstName());
			lnameEle.sendKeys(formData.getLastName());
			emailField.sendKeys(formData.getEmail());
			phoneField.sendKeys(formData.getPhoneNumber());
			
			selectDropdowns(institutionDropdown, formData.getInstitutionType());
			
			institutionNameEle.sendKeys(formData.getInstitutionName());
			
			selectDropdowns(jobRoleDropdown, formData.getJobRole());
			selectDropdowns(departmentDropdown, formData.getDepartment());
			selectDropdowns(needsDropdown, formData.getNeeds());
			
			selectDropdowns(countryDropdown, formData.getCountry());
			
			wait.until(ExpectedConditions.elementToBeClickable(stateDropdown));
			selectDropdowns(stateDropdown, formData.getState());		
			
			logger.info("Submitting form");
			submitBtn.click();

			wait.until(ExpectedConditions.visibilityOf(invalidEmail));
			String invalidTxt = invalidEmail.getText();
            logger.info("Message - {}", invalidTxt);
			
		} catch(Exception e) {
			logger.error("Error in interating with form", e);
		}
	}
	
	private void selectDropdowns(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	public void clearFormFields() {
		try {
			fnameEle.clear();
			lnameEle.clear();
			emailField.clear();
			phoneField.clear();
			institutionNameEle.clear();
		} catch(Exception e) {
			logger.warn("Unable to clear some form fields", e);
		}
	}
	
}
