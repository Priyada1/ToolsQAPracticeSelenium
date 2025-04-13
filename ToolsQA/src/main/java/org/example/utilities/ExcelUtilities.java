package org.example.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtilities {

    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {
        Object[][] data = null;

        XSSFWorkbook xssfWorkbook = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            xssfWorkbook = new XSSFWorkbook(fis);
            Sheet sheet = xssfWorkbook.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum();
            int columnCount = sheet.getRow(0).getLastCellNum();
            data = new Object[rowCount][columnCount];
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < columnCount; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = cell.toString();
                }
            }


        } catch (Exception e) {

            System.out.print("Exception in reading excel file");
            e.printStackTrace();
        }

        xssfWorkbook.close();
        fis.close();
        return data;

    }
}