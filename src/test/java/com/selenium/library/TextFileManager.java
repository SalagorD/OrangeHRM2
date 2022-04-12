package com.selenium.library;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Pure Java class, that reads and writes a given text file.
 * 
 * @author salag
 *
 */
public class TextFileManager {

	public static Logger log = LoggerFactory.getLogger(TextFileManager.class);
	private String fileName;

	/**
	 * This custom constructor receives a file path string, checks if path exists
	 * and if not, then it creates full path with all neccessary folders.
	 * 
	 * @param filePath
	 */
	public TextFileManager(String filePath) {
		try {
			File file = new File(filePath);
			String fullFilePAth = file.getAbsolutePath();
			String parentFolders = file.getParent();
			File file2 = new File(parentFolders);
			if (!file2.exists()) {
				log.info("Specified directory path's folders does not exist");
				log.info("Creating directory's folders only.");
				file2.mkdirs();
			}
			fileName = fullFilePAth;
		} catch (Exception e) {
			log.error("Error", e);
			assertTrue(false);
		}
	}

	/**
	 * This method reads a text file(that was given in Class's constructor) and
	 * prints its data in console.
	 * 
	 * @return
	 */
	public String readFile() {
		String data = null;
		String line = null;
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		StringBuffer stringBuffer = null;
		String newLine = System.lineSeparator();
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			stringBuffer = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line + newLine);
			}
			data = stringBuffer.toString();
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
					fileReader.close();
					stringBuffer = null;
				} catch (Exception e2) {
					log.error("Error", e2);
					assertTrue(false);
				}
			}
		}
		log.info("Reading external file: " + fileName);
		return data;
	}

	/**
	 * This method writes given string into the file(that was given in Class's
	 * constructor).
	 * 
	 * @param inputData
	 */
	public void writeFile(String inputData) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(fileName);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(inputData);
		} catch (Exception e) {
			log.error("Error", e);
		} finally {
			try {
				bufferedWriter.close();
				fileWriter.close();
			} catch (Exception e2) {
				log.error("Error", e2);
				assertTrue(false);
			}
		}
		log.info("Creating external file: " + fileName);
	}

	// public static void main(String[] args) {
	//
	// // testing reading
	// TextFileManager readManager = new
	// TextFileManager("C:/Users/salag/Desktop/Musabay/abc/a11/textExampleFile.txt");
	// String data = readManager.readFile();
	// log.info("Data:\n" + data);
	//
	// // Testing writing
	//
	// TextFileManager writeManager = new TextFileManager(
	// "C:/Users/salag/Desktop/Musabay/abc/a11/textExampleFile.txt");
	// String data2 = "I love Java! \nI will finish selenium training soon. I will
	// get a IT job.";
	// writeManager.writeFile(data2);
	// }

}
