package com.identifycourses.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.identifycourses.base.DriverSetup;

public class BasePage {
	WebDriver driver;
	WebDriverWait wait;
	
	public BasePage() {
		this.driver = DriverSetup.getDriver();
		PageFactory.initElements(driver, this);
		this.wait = DriverSetup.getWait();
	}
}
