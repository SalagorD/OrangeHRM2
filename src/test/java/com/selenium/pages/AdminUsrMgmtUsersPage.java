package com.selenium.pages;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import javax.swing.table.TableCellEditor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.selenium.library.Base;

public class AdminUsrMgmtUsersPage extends Base {

	public static Logger log = LoggerFactory.getLogger(AdminUsrMgmtUsersPage.class);
	private WebDriver driver;

	public AdminUsrMgmtUsersPage(WebDriver _driver) {
		this.driver = _driver;
		try {
			// page synchronization
			WebElement syncElement = myLib.waitForElementVisibility(By.cssSelector("#systemUser-information > a"));
			assertNotNull(syncElement);
			log.info("Navigated page title:[" + driver.getTitle() + "]");
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
	}

	public AdminUsrMgmtUsersPage clickSystemUsersHeader() {
		try {
			myLib.clickButton(By.cssSelector("#systemUser-information > a"));

		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminUsrMgmtUsersPage assertSystemUsersFieldElements_NotVisible() {
		try {
			// wait for element to dissapear
			myLib.sleep(1);
			WebElement sysUsersElem = driver.findElement(By.cssSelector("#systemUser-information > div.inner"));
			List<WebElement> list = sysUsersElem.findElements(By.tagName("li"));
			log.info("List size: " + list.size());
			for (WebElement listElem : list) {
				log.info("" + listElem.getText());
				if (listElem.isDisplayed()) {
					log.info("Element is Displayed");
					assertTrue(false);
				} else {
					log.info("Element is Hidden");
				}
			}
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminUsrMgmtUsersPage assertSystemUsersFieldElements_Visible() {
		try {
			// wait for element to dissapear
			myLib.sleep(1);
			WebElement sysUsersElem = driver.findElement(By.cssSelector("#systemUser-information > div.inner"));
			List<WebElement> list = sysUsersElem.findElements(By.tagName("li"));
			log.info("List size: " + list.size());
			for (WebElement listElem : list) {
				log.info("" + listElem.getText());
				if (listElem.isDisplayed()) {
					log.info("Element is Displayed");
				} else {
					log.info("Element is Hidden");
					assertTrue(false);
				}
			}
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminUsrMgmtUsersPage enterUsername(String userName) {
		try {
			myLib.enterTextField(By.className(""), userName);
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminUsrMgmtUsersPage selectUserRole(String userRole) {
		try {
			myLib.selectFromDropdown(By.cssSelector("#searchSystemUser_userType"), userRole);
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminUsrMgmtUsersPage enterEmployeeName(String employeeName) {
		try {
			WebElement field = driver.findElement(By.cssSelector("#searchSystemUser_employeeName_empName"));
			field.click();
			field.clear();
			field.sendKeys(employeeName);
			WebElement dropdownElem = driver.findElement(By.cssSelector("body > div.ac_results"));
			List<WebElement> list = dropdownElem.findElements(By.tagName("li"));
			log.info("List size: " + list.size());
			for (WebElement listElem : list) {
				Actions action = new Actions(driver);
				action.moveToElement(listElem).perform();
				String elemText = listElem.getText();
				log.info("Element Text:" + elemText);
				if (elemText.contains(employeeName)) {
					// listElem.click();
				}
			}
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminUsrMgmtUsersPage selectStatus(String status) {
		try {
			myLib.selectFromDropdown(By.cssSelector(""), status);
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminUsrMgmtUsersPage clickSearchButton() {
		try {
			myLib.clickButton(By.cssSelector(""));
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminUsrMgmtUsersPage assertUsernameColumn(String userName) {
		try {
			int rowNum = assertColumn("User Name", userName);
			if (rowNum > 1) {
				assertTrue(false);
			}
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminUsrMgmtUsersPage assertUserRoleColumn(String userRole) {
		try {
			assertColumn("User Role", userRole);
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminUsrMgmtUsersPage assertEmployeeNameColumn(String employeeName) {
		try {
			assertColumn("Employee Name", employeeName);
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	public AdminUsrMgmtUsersPage assertStatusColumn(String status) {
		try {
			assertColumn("Status", status);
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

	private int assertColumn(String columnName, String columnText) {
		int rows = 0;
		try {
			WebElement tableElem = driver.findElement(By.cssSelector(""));
			List<WebElement> list = tableElem.findElements(By.tagName("tr"));
			rows = list.size();
			for (WebElement listElem : list) {
				
				//find needed column from row elemet
				WebElement columnElemData = listElem.findElement(By.cssSelector(""));
				String columnElemText = columnElemData.getText();
				if (columnElemText.contains(columnText)) {
					// do nothing
				} else {
					assertTrue(false);
				}

				// find data from column columnName and compare its data to clumn text, if
				// columndata does not containt column text then asserttruefalse
			}
		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return rows;
	}

	public AdminUsrMgmtUsersPage sampleMethod() {
		try {

		} catch (Exception e) {
			log.error("Error:", e);
			assertTrue(false);
		}
		return this;
	}

}
