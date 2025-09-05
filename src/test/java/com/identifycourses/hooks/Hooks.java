

package com.identifycourses.hooks;

import com.identifycourses.base.DriverSetup;
import com.identifycourses.utils.ConfigReader;
import com.identifycourses.utils.AllureReportOpener;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.AfterAll;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public static void setUp() {
        DriverSetup.initializeDriver(ConfigReader.getBrowser());
        DriverSetup.navigateToApplication();
    }

    @After
    public static void tearDown(Scenario scenario) {
        try {
            WebDriver driver = DriverSetup.getDriver();
            if (scenario != null && scenario.isFailed() && driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                // Attach to Cucumber report
                scenario.attach(screenshot, "image/png", "Failed_Scenario_Screenshot");

                // Also save a copy to target/screenshots for convenience
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
                String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9-_]", "_");
                Path screenshotsDir = Paths.get("target", "screenshots");
                Files.createDirectories(screenshotsDir);
                Path out = screenshotsDir.resolve(safeName + "_" + timestamp + ".png");
                Files.write(out, screenshot);
            }
        } catch (Exception ignored) {
            // Swallow screenshot errors to not mask original failure
        } finally {
            DriverSetup.tearDown();
        }
    }

    @AfterAll
    public static void openAllureReport() {
        AllureReportOpener.generateReportAndOpen();
    }
}



