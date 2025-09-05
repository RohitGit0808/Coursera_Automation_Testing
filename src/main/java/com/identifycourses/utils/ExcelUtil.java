package com.identifycourses.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.identifycourses.models.FormData;

public class ExcelUtil {
    private Workbook workbook;
	private Sheet sheet;
	private DataFormatter formatter;
	private String filePath;
    
    private static final Logger logger = LogManager.getLogger(ExcelUtil.class);
    
    public ExcelUtil(String filePath, String sheetName) throws IllegalArgumentException {
        try(FileInputStream fis = new FileInputStream(filePath)) {
            logger.info("Loading Excel file: {}", filePath);
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls")) { 
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new IllegalArgumentException("File format not supported");
            }
            logger.info("Excel file loaded successfully");
            formatter = new DataFormatter();
            logger.info("Sheet found: {}", sheetName);
            sheet = workbook.getSheet(sheetName);
            this.filePath = filePath;
        } catch (IOException e) {
            logger.error("Failed to load Excel file: {}", filePath, e);
        }
    }

    private String getCellValue(int rowIndex, int colIndex) {
        try {
            Row row = sheet.getRow(rowIndex);
            Cell cell = row.getCell(colIndex);
            String value = formatter.formatCellValue(cell);
            logger.debug("Formatted cell value [{}][{}]: {}", rowIndex, colIndex, value);
            return value;
        } catch (Exception e) {
            logger.error("Failed to get cell value [{}][{}]: {}", rowIndex, colIndex, e.getMessage());
            return null;
        }
    }

    public Object[][] getSheetData() {
        try {
            int lastRow = sheet.getLastRowNum();

            Object[][] data = new Object[lastRow][1];  

            for (int row = 1; row <= lastRow; row++) {
                String firstName = getCellValue(row, 0);
                String lastName = getCellValue(row, 1);
                String email = getCellValue(row, 2);
                String phoneNumber = getCellValue(row, 3);
                String institutionType = getCellValue(row, 4);
                String institutionName = getCellValue(row, 5);
                String jobRole = getCellValue(row, 6);
                String department = getCellValue(row, 7);
                String needs = getCellValue(row, 8);
                String country = getCellValue(row, 9);
                String state = getCellValue(row, 10);

                FormData formData = new FormData(
                    firstName, lastName, email, phoneNumber, institutionType,
                    institutionName, jobRole, department, needs, country, state);

                data[row - 1][0] = formData;
            }
            return data;
        } catch (Exception e) {
            logger.error("Failed to get sheet data: {}", e.getMessage());
            return null;
        }
    }


    public void writeData(List<List<String>> data) {
        try {
            if(data == null || data.isEmpty()) {
                logger.info("No data to write");
                return;
            }
            int rowCount = data.size();
            int colCount = data.get(0).size();
            for(int row = 0; row < rowCount; row++) {
                Row sheetRow = sheet.createRow(row);
                for(int col = 0; col < colCount; col++) {
                    Cell cell = sheetRow.createCell(col);
                    cell.setCellValue(data.get(row).get(col));
                }
            }
            try(FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
                logger.info("Data written to sheet: {}", sheet.getSheetName());
    }
        } catch (Exception e) {
            logger.error("Failed to write data to Excel file: {}", e.getMessage());
        }
    }
}