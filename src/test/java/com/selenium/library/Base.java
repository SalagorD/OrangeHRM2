package com.selenium.library;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.selenium.library.SeleniumMethodsLibrary.Browser;

public class Base {

	public static Logger log = LoggerFactory.getLogger(Base.class);
	public static WebDriver driver;
	public static SeleniumMethodsLibrary myLib;
	private String browserType;

	private String sendEmail;
	private String toAddress;
	private String ccAddress;
	private String bccAddress;

	// temp method to get current time
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

	@BeforeClass
	public void beforeAllMethods() {
		try {
			log.info("==================================Automation suite started");

			JavaPropertiesManager writeManager = new JavaPropertiesManager("src/test/resources/session.properties");
			writeManager.setProperty("startTime", getCurrentTime());

			// reading properties from "src/test/resources/config.properties"
			JavaPropertiesManager readManager = new JavaPropertiesManager("src/test/resources/config.properties");
			browserType = readManager.readProperty("browser");
			sendEmail = readManager.readProperty("sendEmail");
			toAddress = readManager.readProperty("toAddress");
			ccAddress = readManager.readProperty("ccAddress");
			bccAddress = readManager.readProperty("bccAddress");

		} catch (Exception e) {
			log.error("Error", e);
		}
	}

	@BeforeMethod
	public void setUpBeforeEachTest() {
		try {
			log.info("=====================Performing setUp");
			myLib = new SeleniumMethodsLibrary();

			if (browserType.toLowerCase().contains("chrome")) {
				driver = myLib.startBrowser(Browser.CHROME);
			} else if (browserType.toLowerCase().contains("firefox")) {
				driver = myLib.startBrowser(Browser.FIREFOX);
			} else if (browserType.toLowerCase().contains("edge")) {
				driver = myLib.startBrowser(Browser.EDGE_CHROMIUM);
			} else if (browserType.toLowerCase().contains("safari")) {
				driver = myLib.startBrowser(Browser.SAFARI);
			} else {
				log.error("Not implemented yet", new NullPointerException());
			}

			//driver.manage().window().maximize();
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
	}

	@AfterMethod
	public void cleanUpAfterEachTest(ITestResult iResult) {
		try {
			log.info("=====================Performing cleanUp");
			// if test fails, screenshot is taken and saved
			if (ITestResult.FAILURE == iResult.getStatus()) {
				// test failed, call capture screenshot method
				log.info("Failed Test: " + iResult.getName());
				myLib.takeScreenShot("target/screenshots/", iResult.getName());
			}
			if (driver != null) {
				// closes the browser only, driver is still live
				driver.close();
			}
		} catch (Exception e) {
			log.error("Error", e);
		}
	}

	@AfterClass
	public void afterAllMethods() {
		try {
			if (driver != null) {
				// kill driver object, driver becomes null
				driver.quit();
			}

			// send email
			if (sendEmail.toLowerCase().contains("on")) {
				EmailManager email = new EmailManager();
				email.setToAddress(toAddress);
				email.setCcAddress(ccAddress);
				email.setBccAddress(bccAddress);
				// adding attachments
				List<String> attachements = new ArrayList<String>();
				attachements = myLib.autoAttachErrorImageToEmail();
				attachements.add("test-output/index.html");
				email.setAttachements(attachements);
				// sending email
				String emailSubject = "Automation test completed!";
				String emailBody = "Automation tests are completed!" + "<br><br>"
						+ "Contact us if you have any questions." + "<br><br>" + "Regards," + "<br>"
						+ "Automation Team";
				email.sendEmail(emailSubject, emailBody);
			}

			log.info("==================================Automation test suite ended");
		} catch (Exception e) {
			log.error("Error", e);
		}
	}

}