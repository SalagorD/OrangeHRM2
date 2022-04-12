package com.selenium.pages;

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

	public void gotOrangeHRMWebsite() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}

	public void enterUsername(String username) {
		WebElement usernameField = driver.findElement(By.id("txtUsername"));
		usernameField.clear();
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		WebElement usernameField = driver.findElement(By.id("txtPassword"));
		usernameField.clear();
		usernameField.sendKeys(password);
	}

	public void clickLoginBtn() {
		WebElement loginBtn = driver.findElement(By.id("btnLogin"));
		loginBtn.click();
	}

	public AdminUsrMgmtUsersPage clickAdminTab() {
		WebElement adminTab = driver.findElement(By.id("menu_admin_viewAdminModule"));
		adminTab.click();
		return new AdminUsrMgmtUsersPage(driver);
	}
}
