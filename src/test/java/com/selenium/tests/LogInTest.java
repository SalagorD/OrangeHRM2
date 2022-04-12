package com.selenium.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.library.Base;
import com.selenium.library.ExcelManager;
import com.selenium.pages.AdminUsrMgmtUsersPage;
import com.selenium.pages.LoginPage;

public class LogInTest extends Base {
	public static Logger log = LoggerFactory.getLogger(LogInTest.class);
	LoginPage loginPage;
	AdminUsrMgmtUsersPage adminUsrMgmtUsersPage;

	// read excel data file and create data provider method
	@DataProvider(name = "loginTestDateSet1")
	private Object[][] calculatorData() {
		Object[][] data = null;
		String excelFile = "src/test/resources/testData/loginCredentialsData.xlsx";
		ExcelManager excel = new ExcelManager(excelFile, 0);
		data = excel.getExcelData();
		return data;
	}

	@Test(dataProvider = "loginTestDateSet1", priority = 1)
	public void testingLogIn(String username, String password) {

		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();

		loginPage.enterUsername(username);

		loginPage.enterPassword(password);

		myLib.sleep(2);

		loginPage.clickLoginBtn();

		loginPage.clickAdminTab();

	}
}
