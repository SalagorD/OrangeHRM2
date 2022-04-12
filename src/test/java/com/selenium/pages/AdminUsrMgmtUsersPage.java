package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminUsrMgmtUsersPage {

	public static Logger log = LoggerFactory.getLogger(AdminUsrMgmtUsersPage.class);
	private WebDriver driver;
	
	public AdminUsrMgmtUsersPage(WebDriver _driver) {
		this.driver = _driver;
	}

}
