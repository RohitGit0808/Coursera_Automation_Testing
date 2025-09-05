package com.identifycourses.tests;

import com.identifycourses.utils.ConfigReader;
import com.identifycourses.utils.ExcelUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import com.identifycourses.base.DriverSetup;
import com.identifycourses.models.FormData;
import com.identifycourses.pages.EnterprisePage;
import com.identifycourses.pages.HomePage;
import com.identifycourses.pages.LanguageLearningPage;
import com.identifycourses.pages.SearchPage;

public class Tester {
	private static final Logger logger = LogManager.getLogger(Tester.class);
	
	HomePage homePage;
	SearchPage searchPage;
	LanguageLearningPage languageLearningPage;
	EnterprisePage enterprisePage;
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) {
		
		DriverSetup.initializeDriver(browser);
	    DriverSetup.navigateToApplication();
	    
	    homePage = new HomePage();
	    searchPage = new SearchPage();
	    languageLearningPage = new LanguageLearningPage();
	    enterprisePage = new EnterprisePage();
	}
	
	@Test(priority = 1)
	public void searchWebDevCourse() {
		homePage.searchCourse("web development");
	}
	
	@Test(priority = 2)
	public void filterWebDevCourses() throws InterruptedException {
		searchPage.filterBeginnerLevel();
		searchPage.filterCourseLanguage();
		searchPage.extractCourseDetails();
		searchPage.navigateToLanguageLearning();
	}
	
	@Test(priority = 3)
	public void learningLanguages() {
		languageLearningPage.clickOnFirstPopularTopic();
		languageLearningPage.fetchAllLanguagesAndCount();
		languageLearningPage.fetchLanguageLevelsAndCount();
	}

	@Test(priority = 4)
	public void navigateToEnterprisePage() {
		enterprisePage.returnToHomePage();
		enterprisePage.navigateToForEnterprise();
		enterprisePage.navigateToCampusProduct();
	}

	@DataProvider(name = "formData")
	public Object[][] formData() {
	    ExcelUtil excelUtil = new ExcelUtil(ConfigReader.getTestDataFile(), ConfigReader.getTestDataSheetNameForm());
	    return excelUtil.getSheetData(); 
	}


	@Test(priority = 5, dataProvider = "formData")
	public void fillForm(FormData formData) {
	    enterprisePage.fillForm(formData);
	    enterprisePage.clearFormFields();
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("Tearing down WebDriver...");
        DriverSetup.tearDown();
	}

}
