package com.selenium.pages;

import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium.library.Base;

public class LeavePage extends Base {

	public static Logger log = LoggerFactory.getLogger(LoginPage.class);
	private WebDriver driver;

	public LeavePage(WebDriver _driver) {
		this.driver = _driver;
		// page synchronization
		WebElement syncElement = myLib.waitForElementVisibility(By.cssSelector(""));
		assertNotNull(syncElement);
		log.info("Navigated page title:[" + driver.getTitle() + "]");
	}

}
