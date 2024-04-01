package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private final String path;
    private Workbook workbook;
    private FileOutputStream fo;

    public ExcelUtility(String path) {
        this.path = path;
        this.workbook = null;
        this.fo = null;
    }

    private void openWorkbook() throws IOException {
        FileInputStream fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        fi.close(); // Close the FileInputStream after opening the workbook
    }

    private void saveAndCloseWorkbook() throws IOException {
        if (workbook != null) {
            fo = new FileOutputStream(path);
            workbook.write(fo);
            workbook.close();
            fo.close();
        }
    }

    public int getRowCount(String sheetName) throws IOException {
        openWorkbook();
        int rowCount = workbook.getSheet(sheetName).getLastRowNum();
        workbook.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        openWorkbook();
        int cellCount = workbook.getSheet(sheetName).getRow(rownum).getLastCellNum();
        workbook.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        openWorkbook();
        Cell cell = workbook.getSheet(sheetName).getRow(rownum).getCell(colnum);
        String data = cell == null ? "" : cell.getStringCellValue();
        workbook.close();
        return data;
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        openWorkbook();
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rownum);
        if (row == null) {
            row = sheet.createRow(rownum);
        }
        Cell cell = row.createCell(colnum);
        cell.setCellValue(data);
        saveAndCloseWorkbook();
    }

    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        openWorkbook();
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Cell cell = workbook.getSheet(sheetName).getRow(rownum).getCell(colnum);
        cell.setCellStyle(style);
        saveAndCloseWorkbook();
    }

    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        openWorkbook();
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Cell cell = workbook.getSheet(sheetName).getRow(rownum).getCell(colnum);
        cell.setCellStyle(style);
        saveAndCloseWorkbook();
    }
}
