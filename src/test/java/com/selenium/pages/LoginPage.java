package com.selenium.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

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
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public LoginPage enterUsername(String username) {
		try {
			myLib.enterTextField(By.id("txtUsername"), username);
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public LoginPage enterPassword(String password) {
		try {
			myLib.enterTextField(By.id("txtPassword"), password);
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public LoginPage clickLoginBtn() {
		try {
			myLib.clickButton(By.id("btnLogin"));
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public LoginPage assertLoginSuccessful() {
		try {
			// todo check if invalid credentials message appeared, if yes then stop the test
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
			List<WebElement> errMsg = driver.findElements(By.cssSelector("#spanMessage"));
			if (errMsg.size() > 0) {
				log.info("Application is overloaded, please try gain later.");
				assertTrue(false);
			}
			List<WebElement> welcomeUserBtn = driver.findElements(By.id("welcome"));
			if (welcomeUserBtn.size() < 1) {
				log.info("Application is overloaded, please try gain later.");
				assertTrue(false);
			}
			myLib.resetWebsiteWaits();
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public LoginPage assertIfInvalidCredentialsMessageIsPresent() {
		try {
			WebElement errMsg = myLib.waitForElementVisibility(By.cssSelector("#spanMessage"));
			String errMsgText = errMsg.getText();
			assertEquals(errMsgText, "Invalid credentials");
			log.info("'" + errMsgText + "' message is present.");
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public LoginPage assertLogin(String dataFlag) {
		try {
			// String title = driver.getTitle();
			if (dataFlag.equalsIgnoreCase("valid")) {
				WebElement welcomeUserBtn = driver.findElement(By.id("welcome"));
				if (welcomeUserBtn.isDisplayed()) {
					// assertEquals("", title);
				} else {
					assertTrue(false);
				}
			} else if (dataFlag.equalsIgnoreCase("invalid")) {
				WebElement loginBtn = driver.findElement(By.cssSelector("#btnLogin"));
				if (loginBtn.isDisplayed()) {
					// assertEquals("", title);
				} else {
					assertTrue(false);
				}
			}
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminPage clickAdminTab() {
		try {
			myLib.clickButton(By.id("menu_admin_viewAdminModule"));
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return new AdminPage(driver);
	}

	public LeavePage clickLeaveTab() {
		try {
			myLib.clickButton(By.id("menu_admin_viewAdminModule"));
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return new LeavePage(driver);
	}

	public RecruitmentPage clickRecruitmentTab() {
		try {
			myLib.clickButton(By.id("menu_admin_viewAdminModule"));
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return new RecruitmentPage(driver);
	}

	public MyInfoPage clickMyInfoTab() {
		try {
			myLib.clickButton(By.id("menu_admin_viewAdminModule"));
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return new MyInfoPage(driver);
	}

}
