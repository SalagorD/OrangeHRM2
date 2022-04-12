package com.selenium.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class will help us read and write excel data
 * 
 * @author salag
 *
 */
public class ExcelManager {
	public static Logger log = LoggerFactory.getLogger(ExcelManager.class);
	private static String filePath;
	private static Workbook wb;
	private static Sheet sh;

	public ExcelManager(String excelFilePath, String sheetName) {
		try {
			File excelDataFile = new File(excelFilePath);
			filePath = excelDataFile.getAbsolutePath();
			log.info("Reading Excel File --->" + filePath);
			FileInputStream fis = new FileInputStream(filePath);
			wb = getWorkbook(fis, filePath);
			sh = wb.getSheet(sheetName);
		} catch (Exception e) {
			log.error("Error: ", e);
		}
	}

	public ExcelManager(String excelFilePath, int sheetIndex) {
		try {
			File excelDataFile = new File(excelFilePath);
			filePath = excelDataFile.getAbsolutePath();
			log.info("Reading Excel File --->" + filePath);
			FileInputStream fis = new FileInputStream(filePath);
			wb = getWorkbook(fis, filePath);
			sh = wb.getSheetAt(sheetIndex);
		} catch (Exception e) {
			log.error("Error: ", e);
		}
	}

	/**
	 * This method will help us read the data.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	public String readExcelDataCell(int rowIndex, int colIndex) {
		String cellData = null;
		try {
			Row row = sh.getRow(rowIndex);
			if (row != null) {
				Cell cell = row.getCell(colIndex);
				cellData = formatDataCellToString(cell);
			}
		} catch (Exception e) {
			log.error("Error: ", e);
		}
		return cellData;
	}

	public String[][] getExcelData() {
		String[][] arrayExcelData = null;
		try {
			Iterator<Row> iterator = sh.iterator();
			Row tempRow = sh.getRow(0);
			if (tempRow != null) {
				int totalCols = tempRow.getPhysicalNumberOfCells();
				int totalRows = sh.getPhysicalNumberOfRows();
				arrayExcelData = new String[totalRows - 1][totalCols];
				int iRowCount = 0;
				while (iterator.hasNext()) {
					Row row = iterator.next();
					// skipping row1, because its table header info
					if (iRowCount > 0) {
						Iterator<Cell> colIterator = row.iterator();
						int iColCount = 0;
						while (colIterator.hasNext()) {
							Cell cell = colIterator.next();
							// need to format the cells before read it as a string
							String data = formatDataCellToString(cell);
							arrayExcelData[iRowCount - 1][iColCount] = data;
							//log.info("Row: [" + iRowCount + "], Col: [" + iColCount + "], Data: [" + data + "]");
							iColCount++;
						}
					}
					iRowCount++;
				}
			}

		} catch (Exception e) {
			log.error("Error: ", e);
		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (IOException e) {
					log.error("Error: ", e);
				}
			}
		}
		return arrayExcelData;
	}

	/**
	 * This helper method is returning a string from given cell
	 * 
	 * @param cell
	 * @return
	 */
	private String formatDataCellToString(Cell cell) {
		String cellString = null;
		try {
			DataFormatter formatter = new DataFormatter();
			cellString = formatter.formatCellValue(cell);
		} catch (Exception e) {
			log.error("Error: ", e);
		}
		return cellString;
	}

	private Workbook getWorkbook(FileInputStream fis, String excelFilePath) {
		Workbook workbook = null;
		try {
			if (excelFilePath.toLowerCase().endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else if (excelFilePath.toLowerCase().endsWith(".xls")) {
				workbook = new HSSFWorkbook(fis);
			} else {
				throw new IllegalArgumentException("The specified file is not Excel data file.");
			}
		} catch (Exception e) {
			log.error("Error: ", e);
		}
		return workbook;
	}

//	public static void main(String[] args) {
//		// reading xls format
//		ExcelManager exl = new ExcelManager("src/test/resources/testData/CalculaterTestData_OLD.xls", 0);
//		String mydata = exl.readExcelDataCell(3, 3);
//		log.info("Old Excell Cell data:[" + mydata + "]");
//		log.info("");
//
//		ExcelManager exl21 = new ExcelManager("src/test/resources/testData/CalculaterTestData.xlsx", 0);
//		String mydata21 = exl21.readExcelDataCell(0, 0);
//		log.info("Cell data:[" + mydata21 + "]");
//		log.info("");
//
//		String mydata211 = exl21.readExcelDataCell(1, 1);
//		log.info("Cell data:[" + mydata211 + "]");
//		log.info("");
//
//		log.info("All excel data:");
//		log.info("" + exl.getExcelData());
//
//	}

}
