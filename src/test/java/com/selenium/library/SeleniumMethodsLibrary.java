package com.selenium.library;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

/***
 * This class is to create wrapper methods for basic slenium functions and some
 * java methods that assist in doing so.
 * 
 * @author Denis Salagor Class is created 2/22/2022
 */
public class SeleniumMethodsLibrary {

	public static Logger log = LoggerFactory.getLogger(SeleniumMethodsLibrary.class);
	private WebDriver driver;
	private int waitTimeInSec = 10;

	// ############################################################################################################
	// ############################################################################################################
	// Getters and Setters below:

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	// ############################################################################################################
	// ############################################################################################################
	// Selenium WebDriver methods below:

	/***
	 * This enum is for browser options.
	 * 
	 * @author salag
	 *
	 */
	public enum Browser {
		CHROME, FIREFOX, SAFARI, EDGE_CHROMIUM
	}

	/***
	 * This method starts browser and receives Browser enum as parameter.
	 * 
	 * @param browser
	 * @return
	 */
	public WebDriver startBrowser(Browser browser) {
		try {
			switch (browser) {
			case CHROME:
				driver = startChromeBrowser(browser);
				break;

			case FIREFOX:
				driver = startFirefoxBrowser(browser);
				break;

			case SAFARI:
				driver = startSafariBrowser(browser);
				break;

			case EDGE_CHROMIUM:
				driver = startEdgeChromiumBrowser(browser);
				break;

			default:
				log.info("Currently framework does not support: [" + browser + "]");
				log.info("Default browser set to 'Chrome'");
				driver = startChromeBrowser(browser);
				break;

			}
			// printDriverManagerInfo();
			driver.manage().deleteAllCookies();
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method starts Microsoft Edge Chromium browser
	 * 
	 * @return WebDriver
	 */
	private WebDriver startEdgeChromiumBrowser(Browser browser) {
		try {
			// System.setProperty("webdriver.edge.driver",
			// "src/test/resources/drivers/msedgedriver.exe");
			startWebDriverManager(browser);
			driver = new EdgeDriver();
			setWebsiteWaits();
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method starts Safari browser
	 * 
	 * @return WebDriver
	 */
	private WebDriver startSafariBrowser(Browser browser) {
		try {
			startWebDriverManager(browser);
			driver = new SafariDriver();
			setWebsiteWaits();
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method starts FireFox browser.
	 * 
	 * @return WebDriver
	 */
	private WebDriver startFirefoxBrowser(Browser browser) {
		try {
			startWebDriverManager(browser);
			driver = new FirefoxDriver();
			setWebsiteWaits();
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method starts Google Chrome browser
	 * 
	 * @return WebDriver
	 */
	private WebDriver startChromeBrowser(Browser browser) {
		try {
			// System.setProperty("webdriver.chrome.driver",
			// "src/test/resources/drivers/chromedriver.exe");
			startWebDriverManager(browser);
			driver = new ChromeDriver();
			setWebsiteWaits();
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return driver;
	}

	/***
	 * This method combines Selenium pageLoadTimeout, implicitlyWait, scriptTimeout.
	 */
	private void setWebsiteWaits() {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTimeInSec));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(waitTimeInSec));
			driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(waitTimeInSec));
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
	}

	/**
	 * Implementaion of WebDriverManagers from:
	 * https://bonigarcia.dev/webdrivermanager/,
	 * 
	 * @param browser
	 */
	private void startWebDriverManager(Browser browser) {
		if (browser.equals(Browser.CHROME)) {
			WebDriverManager.chromedriver().setup();
		} else if (browser.equals(Browser.EDGE_CHROMIUM)) {
			// delete win64 folder if win32 is not downloaded
			WebDriverManager.edgedriver().arch32().setup();
		} else if (browser.equals(Browser.FIREFOX)) {
			WebDriverManager.firefoxdriver().setup();
		} else if (browser.equals(Browser.SAFARI)) {
			WebDriverManager.safaridriver().setup();
		} else {
			log.error("Currently [" + browser + "] is not supported.");
		}
	}

	// ############################################################################################################
	// ############################################################################################################
	// Selenium methods below:

	// ############################################################################################################
	// ############################################################################################################
	// Pure Java methods below:

	/***
	 * Method that pauses for set amount of seconds.
	 */
	public void sleep(double inSeconds) {
		try {
			Thread.sleep((long) (inSeconds * 1000));
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}

	}

	/**
	 * Method that takes screenshot and saves it in given path with given name plus
	 * timestamp.
	 * 
	 * @param fileLocationPath
	 * @param partialScreenshotName
	 * @return Abolute path of the saved screenshot
	 */
	public String takeScreenShot(String fileLocationPath, String partialScreenshotName) {
		String finalScreenshotAbsPathNFileName = null;
		try {
			// Creating absolutepath and creating screenshot name using timestamp
			String timestamp = getCurrentTime();
			File file = new File(fileLocationPath);
			if (!file.exists()) {
				log.info("Specified directory to save screenshots does not exist, creating path...");
				file.mkdirs();
			}
			String absFilePath = file.getAbsolutePath();
			finalScreenshotAbsPathNFileName = absFilePath + "\\" + partialScreenshotName + "_" + timestamp + ".png";

			// taking screenshot
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(scrFile, new File(finalScreenshotAbsPathNFileName));
			log.info("Screenshot taken and saved in: [" + finalScreenshotAbsPathNFileName + "]");
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return finalScreenshotAbsPathNFileName;
	}

	/**
	 * Method that returns a string with timestamp.
	 * 
	 * @return
	 */
	public String getCurrentTime() {
		String finalTimeStamp = null;
		try {
			finalTimeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuuMMdd_HH_mm_ss_SSS"))
					.toString();
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return finalTimeStamp;
	}

	/**
	 * This method is creating and returning a list of absolute path's of
	 * screenshots that were taken after current test was started. It gets test
	 * start time from "src/test/resources/session.properties", extracts timestamp
	 * of all the screenshots, and then compares them, if screenshot's timestamp is
	 * greater than test start time, it will add its absoulute path to the list.
	 * 
	 * @return
	 */
	public List<String> autoAttachErrorImageToEmail() {
		List<String> fileNames = new ArrayList<String>();
		try {
			JavaPropertiesManager propReader = new JavaPropertiesManager("src/test/resources/session.properties");
			String tempStartTime = propReader.readProperty("startTime").replace("_", "");

			// parsing our test start timestamp from string to long
			long startTime = Long.parseLong(tempStartTime);

			// first check if screenshot folder has files
			File file = new File("target/screenshots/");
			if (file.isDirectory()) {
				if (file.list().length > 0) {

					// this creates file array with all files from folder
					File[] screenshotFiles = file.listFiles();

					for (int i = 0; i < screenshotFiles.length; i++) {

						// check if file is a file and not a folder/directory
						if (screenshotFiles[i].isFile()) {

							// get the name of the file
							String eachFileName = screenshotFiles[i].getName();

							// removing underscores and extracting timestamp from screenshot file name
							String nameNoUnderScore = eachFileName.replace("_", "");
							String extractedTimestampFromFileName = nameNoUnderScore
									.substring(nameNoUnderScore.length() - 21, nameNoUnderScore.length() - 4);

							// parsing our extracted timestamp from string to long
							long screenshotFileTime = Long.parseLong(extractedTimestampFromFileName);

							// comparing if our screenshot was teken after the current test started
							if (screenshotFileTime > startTime) {
								// if true, finding and adding absolute path to our list
								String relativePath = "target/screenshots/" + eachFileName;
								if (!relativePath.isEmpty() && relativePath != null) {
									File file2 = new File(relativePath);
									String finalPath = file2.getAbsolutePath();
									fileNames.add(finalPath);
									log.info("Attaching screenshot: [" + eachFileName + "]");
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return fileNames;
	}

}
