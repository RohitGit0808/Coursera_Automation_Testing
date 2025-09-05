


package com.identifycourses.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.identifycourses.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	private static final Logger logger = LogManager.getLogger(DriverSetup.class);
    private static WebDriver driver = null;
    private static WebDriverWait wait;

	private DriverSetup() {}
	
	/**
    * Initialize WebDriver based on browser configuration
    * 
    * @param browserName Browser name (chrome/edge)
    */
    public static void initializeDriver(String browserName) {
        try {
            logger.info("Initializing WebDriver for browser: {}", browserName);

            switch (browserName.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        	        driver = new ChromeDriver(chromeOptions);
        	        driver.manage().window().maximize();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
        	        driver = new EdgeDriver(edgeOptions);
                    driver.manage().window().maximize();
                    break;

                default:
                    throw new IllegalArgumentException("Browser not supported: " + browserName);
            }

            // Set timeouts
            driver.manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(ConfigReader.getImplicitWait()));
            driver.manage().timeouts().pageLoadTimeout(
                    Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));

            // Initialize WebDriverWait
            wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));

            logger.info("WebDriver initialized successfully");

        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver: {}", e.getMessage());
            throw new RuntimeException("Driver initialization failed", e);
        }
    }
    
    /**
     * Navigate to application URL
     */
    public static void navigateToApplication() {
    	try {
    		String url = ConfigReader.getProperty("app.url");
            logger.info("Navigating to application URL: {}", url);
            driver.get(url);		
    	} catch(Exception e) {
    		logger.error("Failed to navigate to application: {}", e.getMessage());
            throw new RuntimeException("Navigation failed", e);
    	}
    }
    
    /**
     * Close browser and cleanup resources
     */
    public static void tearDown() {
        try {
            if (driver != null) {
                logger.info("Closing browser and cleaning up resources");
                driver.quit();
                logger.info("Browser closed successfully");
            }
        } catch (Exception e) {
            logger.error("Error during teardown: {}", e.getMessage());
        }
    }
    
    /**
     * Get WebDriver instance
     * 
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Get WebDriverWait instance
     * 
     * @return WebDriverWait instance
     */
    public static WebDriverWait getWait() {
        return wait;
    }
}








