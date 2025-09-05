package com.identifycourses.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {
	private static final Logger logger = LogManager.getLogger(ConfigReader.class);
	private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
    
    static {
        loadProperties();
    }
    
    /**
     * Load properties from config file
     */
    private static void loadProperties() {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fileInputStream);
            fileInputStream.close();
            logger.info("Configuration properties loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load configuration properties: {}", e.getMessage());
            throw new RuntimeException("Configuration file not found", e);
        }
    }
    
    /**
     * Get property value by key
     * 
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found for key: {}", key);
        }
        return value;
    }
    
    /**
     * Get browser name from config
     * 
     * @return Browser name
     */
    public static String getBrowser() {
        return getProperty("browser");
    }
    
    /**
     * Get application URL from config
     * 
     * @return Application URL
     */
    public static String getAppUrl() {
        return getProperty("app.url");
    }

    /**
     * Get implicit wait timeout
     * 
     * @return Implicit wait in seconds
     */
    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    /**
     * Get explicit wait timeout
     * 
     * @return Explicit wait in seconds
     */
    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }
    
    /**
     * Get page load timeout
     * 
     * @return Page load timeout in seconds
     */
    public static int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout"));
    }
    
    /**
     * Get test data file path
     * 
     * @return Test data file path
     */
    public static String getTestDataFile() {
        return getProperty("test.data.file");
    }
    
    /**
     * Get test data sheet name
     * 
     * @return Test data sheet name
     */
    public static String getTestDataSheetNameSearch() {
        return getProperty("test.data.sheetname.search");
    }

    /**
     * Get test data sheet name form
     *
     * @return Test data sheet name form
     */
    public static String getTestDataSheetNameForm() {
        return getProperty("test.data.sheetname.form");
    }
    
    /**
     * Get test data sheet name results
     * 
     * @return Test data sheet name results
     */
    public static String getTestDataSheetNameResults() {
        return getProperty("test.data.sheetname.results");
    }

    /**
     * Get screenshot path
     * 
     * @return Screenshot path
     */
    public static String getScreenshotPath() {
        return getProperty("screenshot.path");
    }
}
