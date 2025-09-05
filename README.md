# Coursera Automation Project

Used [coursera.org](https://coursera.org) for automation workflows.

## Features Implemented

- **Multi-browser execution** : Supports Chrome and Edge (via DriverSetup).
- **Hybrid Framework** : Combines Page Object Model (POM), Data-Driven Testing, and BDD using Cucumber.
- **Reusable components** : Core functionalities are modularized for maintainability.
- **Exception handling** : Robust try-catch blocks with meaningful error messages.
- **Synchronization** : Managed using WebDriverWait and implicit waits.
- **Locators** : Uses reliable locators like id, name, and xpath.
- **Console logging** : Outputs key data such as course details and validation messages.
- **Screenshot capture** : Captures screenshots for validation and reporting.
- **Clean code** : Follows Java conventions with comments and structured headers.

## Folder Structure

```
.settings/
src/
    └── main/
    |   ├── java/
    |   |   ├── base/
    |   |   |   └── DriverSetup.java
    |   |   ├── models/
    |   |   |   └── FormData.java
    |   |   |   └── LanguageDetails.java
    |   |   |   └── LanguageLevels.java
    |   |   |   └── SearchData.java
    |   |   |   └── SearchResults.java
    |   |   ├── pages/
    |   |   |   ├── BasePage.java
    |   |   |   ├── EnterprisePage.java
    |   |   |   ├── HomePage.java
    |   |   |   ├── LanguageLearningPage.java
    |   |   |   ├── SearchPage.java
    |   |   ├── utils/
    |   |       ├── AllureReportOpener.java
    |   |       ├── ConfigReader.java
    |   |       ├── CsvUtil.java
    |   |       ├── ExcelUtil.java
    |   |       ├── JsonUtil.java
    |   |   
    |   └── resources/  
    |           ├── config.properties
    |           ├── log4j2.xml        
    └── test/
        ├── java/
        |   ├── hooks/
        |   |   └── Hooks.java
        |   ├── runners/
        |   |   └── RerunTestRunner.java
        |   |   └── TestRunner.java
        |   ├── stepdefinitions/
        |       └── EnterpriseFormSteps.java  
        |       └── EnterpriseNavigationSteps.java 
        |       └── FilterExtractSteps.java 
        |       └── LanguageDetailsSteps.java 
        |       └── SearchCoursesSteps.java 
        └── resources/
                ├── features/
          	|	 ├── 1_search_courses.feature
    		    |	 ├── 2_filter_extract.feature
    		    |	 ├── 3_language_details.feature
    		    |	 ├── 4_navigate_enterprise.feature
    		    |	 ├── 5_enterprise_form.feature
    		    |
    		    ├── testdata/
      	      	|   	 ├── course_details.csv
    		    |	 ├── language_details.json
    		    |	 ├── test_data.xlsx
    		    |	
    		    ├── allure.properties
    		    ├── testng.xml	 
target/
     ├── allure-results/
     └── screenshots/     
test-output/
pom.xml
```

## Files Functionality
- **base/DriverSetup.java** : WebDriver setup
- **hooks/Hooks.java** : Driver Manipulation and Screenshot
- **PageObjects/** : Contains POM of all pages
- **StepDefination** : Contains Common step and testCases
- **testRunner**: Contains TestNG runner
- **features/**: Contains scenario and feature of test cases
- **AllureReportOpener.java** : Manages Allure Reports
- **ExcelUtils.java** : Utility for reading/writing Excel data
- **CsvUtil.java**: Manages csv file
- **JsonUtil.java**: Manages json file

## File Resources
- **test_data.xlsx** : Contains input data
- **config.properties**: contains driver name, environment,OS and website.
- **log4j2.xml**: Contains logging functionality

## Steps Performed

1. SEARCH → WEB DEVELOPMENT (SELECT FROM SUGGESTION) → CLICK ENGLISH FROM LIST → CLICK BEGINNER FROM LIST → SCRAPE FIRST 2 → PRINT NAME | RATING | DURATION
2. LIST OF LANGUAGE → AND TOTAL NO OF EACH COURSES EACH
3. ENTERPRISE → FILL FORM → INVALID INPUT → PRINT ERROR MESSAGE

## Other Files:
- **pom.xml** : contains required dependencies

## Tech Stack

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF4D4D?style=for-the-badge)
![Cucumber](https://img.shields.io/badge/Cucumber-2B6B2B?style=for-the-badge&logo=cucumber&logoColor=white)
![Apache POI](https://img.shields.io/badge/Apache_POI-1D2D50?style=for-the-badge&logo=apache&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Log4j2](https://img.shields.io/badge/Log4j2-CC3300?style=for-the-badge) 
![Excel](https://img.shields.io/badge/Excel-217346?style=for-the-badge&logo=microsoft-excel&logoColor=white)
![XML File](https://img.shields.io/badge/XML_File-FF6600?style=for-the-badge&logo=xml&logoColor=white)
![TXT File](https://img.shields.io/badge/TXT_File-4479A1?style=for-the-badge&logo=textedit&logoColor=white)
![Properties File](https://img.shields.io/badge/Properties_File-6D4C41?style=for-the-badge)
![Google Chrome](https://img.shields.io/badge/Chrome-4285F4?style=for-the-badge&logo=googlechrome&logoColor=white)
![Microsoft Edge](https://img.shields.io/badge/Edge-0078D7?style=for-the-badge&logo=microsoft-edge&logoColor=white)
![Allure Report](https://img.shields.io/badge/Allure_Report-FFD700?style=for-the-badge&logo=allure&logoColor=black)
![Extent Reports](https://img.shields.io/badge/Extent_Reports-007ACC?style=for-the-badge)
![Cucumber Report](https://img.shields.io/badge/Cucumber_Report-2B6B2B?style=for-the-badge&logo=cucumber&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)


## License

![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)

This project is licensed under the **[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)**.

You may use, modify, and distribute this code in compliance with the license terms.


> ⚠️ **Disclaimer**:  
> This project is developed strictly for educational and demonstration purposes.  
> It is not affiliated with, maintained by, or officially connected to **Coursera**.  
> Use of automation tools on public platforms may violate their terms of service.  
> Please use responsibly and at your own discretion.