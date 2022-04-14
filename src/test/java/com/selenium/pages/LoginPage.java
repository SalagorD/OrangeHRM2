package com.selenium.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium.library.Base;

public class LoginPage extends Base {
	public static Logger log = LoggerFactory.getLogger(LoginPage.class);

	private WebDriver driver;

	public LoginPage(WebDriver _driver) {
		this.driver = _driver;
	}

	public LoginPage gotOrangeHRMWebsite() {
		try {
			driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return this;
	}

	public LoginPage enterUsername(String username) {
		try {
			WebElement usernameField = driver.findElement(By.id("txtUsername"));
			usernameField.clear();
			usernameField.sendKeys(username);
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return this;
	}

	public LoginPage enterPassword(String password) {
		try {
			WebElement usernameField = driver.findElement(By.id("txtPassword"));
			usernameField.clear();
			usernameField.sendKeys(password);
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return this;
	}

	public LoginPage clickLoginBtn() {
		try {
			WebElement loginBtn = driver.findElement(By.id("btnLogin"));
			loginBtn.click();
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminUsrMgmtUsersPage clickAdminTab() {
		try {
			WebElement adminTab = driver.findElement(By.id("menu_admin_viewAdminModule"));
			adminTab.click();
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return new AdminUsrMgmtUsersPage(driver);
	}

	public LoginPage assertIfInvalidCredentialsMessageIsPresent() {
		try {
			WebElement errMsg = myLib.waitForElementVisibility(By.cssSelector("#spanMessage"));
			String errMsgText = errMsg.getText();
			assertEquals(errMsgText, "Invalid credentials");
			log.info("'"+errMsgText+"' message is present.");
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return this;
	}

}
