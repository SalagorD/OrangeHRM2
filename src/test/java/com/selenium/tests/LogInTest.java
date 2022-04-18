package com.selenium.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.selenium.library.Base;
import com.selenium.pages.LoginPage;

public class LogInTest extends Base {
	public static Logger log = LoggerFactory.getLogger(LogInTest.class);
	LoginPage loginPage;

	@Test(priority = 1, groups = "SmokeTest, Regression")
	public void TC_OHRM_Login_1() {
		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("admin123");
		loginPage.clickLoginBtn();
	}

	@Test(priority = 1, enabled = true, groups = "Regression")
	public void TC_OHRM_Login_2() {
		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("earth123");
		loginPage.clickLoginBtn();
		loginPage.assertIfInvalidCredentialsMessageIsPresent();
	}

	@Test(priority = 1, enabled = true, groups = "Regression")
	public void TC_OHRM_Login_3() {
		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();
		loginPage.enterUsername("Joe");
		loginPage.enterPassword("admin123");
		loginPage.clickLoginBtn();
		loginPage.assertIfInvalidCredentialsMessageIsPresent();
	}

	@Test(priority = 1, enabled = true, groups = "Regression")
	public void TC_OHRM_Login_4() {
		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();
		loginPage.enterUsername("Joe");
		loginPage.enterPassword("earth123");
		loginPage.clickLoginBtn();
		loginPage.assertIfInvalidCredentialsMessageIsPresent();
	}
}
