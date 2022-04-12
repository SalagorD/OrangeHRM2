package com.selenium.library;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Pure Java class, that reads and writes properties from
 * src/test/resources/config.properties file.
 * 
 * @author salag
 *
 */
public class JavaPropertiesManager {

	public static Logger log = LoggerFactory.getLogger(JavaPropertiesManager.class);
	private String propertiesFile;
	private Properties prop;
	private FileInputStream input;
	private FileOutputStream output;

	public JavaPropertiesManager(String propertiesFilePath) {
		try {
			if (propertiesFilePath.length() > 0) {
				propertiesFile = propertiesFilePath;
				prop = new Properties();
			}
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
	}

	public String readProperty(String key) {
		// read in
		String value = null;
		try {
			input = new FileInputStream(propertiesFile);
			prop.load(input);
			value = prop.getProperty(key);
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
		return value;
	}

	public void setProperty(String key, String value) {
		// writting out
		try {
			output = new FileOutputStream(propertiesFile);
			prop.setProperty(key, value);
			prop.store(output, null);
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
	}

//	public static void main(String[] args) {
//		JavaPropertiesManager myProp = new JavaPropertiesManager("C:/Users/salag/Desktop/Musabay/test.properties");
//		myProp.setProperty("name", "frank11");
//		// myProp.setProperty("country", "USA22");
//
//	}

}
