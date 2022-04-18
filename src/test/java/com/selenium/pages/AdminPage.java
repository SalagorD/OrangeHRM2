package com.selenium.pages;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium.library.Base;

public class AdminPage extends Base {

	public static Logger log = LoggerFactory.getLogger(AdminPage.class);
	private WebDriver driver;

	public AdminPage(WebDriver _driver) {
		this.driver = _driver;
		// page synchronization
		WebElement syncElement = myLib.waitForElementVisibility(By.cssSelector("#menu_admin_UserManagement"));
		assertNotNull(syncElement);
		log.info("Navigated page title:[" + driver.getTitle() + "]");
	}

	public AdminUsrMgmtUsersPage hoverUserManagementTab_clickUsers() {
		try {
			myLib.hoverThenSelectFromDropdown(By.cssSelector("#menu_admin_UserManagement"), "Users");
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return new AdminUsrMgmtUsersPage(driver);
	}

	public AdminPage hoverJobTab_clickJobTitles() {
		try {
			myLib.hoverThenSelectFromDropdown(By.cssSelector("#menu_admin_Job"), "Job Titles");
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return this;
	}
}
