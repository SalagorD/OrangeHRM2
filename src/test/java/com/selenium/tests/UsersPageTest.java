package com.selenium.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.selenium.library.Base;
import com.selenium.pages.AdminPage;
import com.selenium.pages.AdminUsrMgmtUsersPage;
import com.selenium.pages.LoginPage;

public class UsersPageTest extends Base {
	public static Logger log = LoggerFactory.getLogger(UsersPageTest.class);
	LoginPage loginPage;
	AdminPage adminPage;
	AdminUsrMgmtUsersPage adminUsrMgmtUsersPage;

	@Test(priority = 1, enabled = false, groups = "Regression")
	public void TC_OHRM_Admin_Users_SystemUsersField_1() {
		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("admin123");
		loginPage.clickLoginBtn();
		loginPage.assertLoginSuccessful();
		adminPage = loginPage.clickAdminTab();
		adminUsrMgmtUsersPage = adminPage.hoverUserManagementTab_clickUsers();
		adminUsrMgmtUsersPage.assertSystemUsersFieldElements_Visible();
	}

	@Test(priority = 1, enabled = false, groups = "Regression")
	public void TC_OHRM_Admin_Users_SystemUsersField_2() {
		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("admin123");
		loginPage.clickLoginBtn();
		loginPage.assertLoginSuccessful();
		adminPage = loginPage.clickAdminTab();
		adminUsrMgmtUsersPage = adminPage.hoverUserManagementTab_clickUsers();
		adminUsrMgmtUsersPage.clickSystemUsersHeader();
		adminUsrMgmtUsersPage.assertSystemUsersFieldElements_NotVisible();
	}

	@Test(priority = 1, enabled = false, groups = "Regression")
	public void TC_OHRM_Admin_Users_SystemUsersField_3() {
		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("admin123");
		loginPage.clickLoginBtn();
		loginPage.assertLoginSuccessful();
		adminPage = loginPage.clickAdminTab();
		adminUsrMgmtUsersPage = adminPage.hoverUserManagementTab_clickUsers();
		adminUsrMgmtUsersPage.clickSystemUsersHeader();
		adminUsrMgmtUsersPage.clickSystemUsersHeader();
		adminUsrMgmtUsersPage.assertSystemUsersFieldElements_Visible();
	}

	@Test(priority = 1, enabled = true, groups = "Regression")
	public void TC_OHRM_Admin_Users_SearchBtn_1() {
		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("admin123");
		loginPage.clickLoginBtn();
		loginPage.assertLoginSuccessful();
		adminPage = loginPage.clickAdminTab();
		adminUsrMgmtUsersPage = adminPage.hoverUserManagementTab_clickUsers();
		adminUsrMgmtUsersPage.enterUsername("jhaey");
		adminUsrMgmtUsersPage.clickSearchButton();
		adminUsrMgmtUsersPage.assertUsernameColumn("jhaey");
	}

	@Test(priority = 1, enabled = true, groups = "Regression")
	public void TC_OHRM_Admin_Users_SearchBtn_2() {
		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("admin123");
		loginPage.clickLoginBtn();
		loginPage.assertLoginSuccessful();
		adminPage = loginPage.clickAdminTab();
		adminUsrMgmtUsersPage = adminPage.hoverUserManagementTab_clickUsers();
		adminUsrMgmtUsersPage.selectUserRole("Admin");
		adminUsrMgmtUsersPage.clickSearchButton();
		adminUsrMgmtUsersPage.assertUserRoleColumn("Admin");
	}

	@Test(priority = 1, enabled = true, groups = "Regression")
	public void TC_OHRM_Admin_Users_SearchBtn_3() {
		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("admin123");
		loginPage.clickLoginBtn();
		loginPage.assertLoginSuccessful();
		adminPage = loginPage.clickAdminTab();
		adminUsrMgmtUsersPage = adminPage.hoverUserManagementTab_clickUsers();
		adminUsrMgmtUsersPage.enterEmployeeName("Joe Root");
		adminUsrMgmtUsersPage.clickSearchButton();
		adminUsrMgmtUsersPage.assertEmployeeNameColumn("Joe Root");
	}

	@Test(priority = 1, enabled = true, groups = "Regression")
	public void TC_OHRM_Admin_Users_SearchBtn_4() {
		loginPage = new LoginPage(driver);
		loginPage.gotOrangeHRMWebsite();
		loginPage.enterUsername("Admin");
		loginPage.enterPassword("admin123");
		loginPage.clickLoginBtn();
		loginPage.assertLoginSuccessful();
		adminPage = loginPage.clickAdminTab();
		adminUsrMgmtUsersPage = adminPage.hoverUserManagementTab_clickUsers();
		adminUsrMgmtUsersPage.selectStatus("Enabled");
		adminUsrMgmtUsersPage.clickSearchButton();
		adminUsrMgmtUsersPage.assertStatusColumn("Enabled");
	}

}
