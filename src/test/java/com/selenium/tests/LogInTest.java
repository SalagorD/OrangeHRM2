package com.selenium.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.library.Base;
import com.selenium.library.ExcelManager;
import com.selenium.pages.LoginPage;

public class LogInTest extends Base {
	public static Logger log = LoggerFactory.getLogger(LogInTest.class);
	LoginPage loginPage;

	@Test(priority = 1, enabled = true, groups = "SmokeTest, Regression")
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

	// read excel data file and create data provider method
	@DataProvider(name = "LoginDataSet1")
	private Object[][] calculatorData() {
		Object[][] data = null;
		String excelFile = "src/test/resources/testData/loginCredentialsData.xlsx";
		ExcelManager excel = new ExcelManager(excelFile, 0);
		data = excel.getExcelData();
		return data;
	}

	@Test(dataProvider = "LoginDataSet1", priority = 1, enabled = true, groups = "DataDriven, Regression")
	public void TC_OHRM_Login_5(String username, String password, String dataFlag) {
		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginBtn();
		loginPage.assertLogin(dataFlag);
	}

}
