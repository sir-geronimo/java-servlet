package jimenez.enger.helpers;

import jimenez.enger.Config;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelFile {
    public File file;
    public Workbook workbook;
    public Sheet sheet;
    public Row row;
    public String filepath = Config.FILE_PATH + "xcrud_db.xlsx";
    FileInputStream fis;

    public void Init(String filename, FileInputStream file) throws Exception{
        // Verify file extension
        if (filename.endsWith("xlsx")) {
            if (file == null) {
                this.workbook = new XSSFWorkbook();
            } else {
                this.workbook = new XSSFWorkbook(file);
            }
        } else if (filename.endsWith("xls")) {
            if (file == null) {
                this.workbook = new HSSFWorkbook();
            } else {
                this.workbook = new HSSFWorkbook(file);
            }
        } else {
            throw new Exception("Invalid file name, must be xls or xlsx");
        }
    }
    public void Open(int sheetNumber) throws Exception {
        // Init file
        Init(filepath, null);

        file = new File(filepath);

        if (!file.exists()) {
            // Initiate sheets
            workbook.createSheet("Products");
            workbook.createSheet("Dashboard");
//            workbook.createSheet("Users");

            // set sheet styles
            setHeaders();

            // Close and save file
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
        }

        // Open file
        fis = new FileInputStream(file);
        workbook = WorkbookFactory.create(fis);

        // Open sheet
        sheet = workbook.getSheetAt(sheetNumber);
    }
    public void Close() throws Exception {
        // Save changes
        fis.close();

        // Save file
        FileOutputStream fos = new FileOutputStream(filepath);
        workbook.write(fos);
        workbook.close();
        fos.close();
    }
    private void setHeaders() {
        sheet = workbook.getSheetAt(0);
        row = sheet.createRow(0);

        // Set columns width
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 28 * 256);
        sheet.setColumnWidth(3, 14 * 256);
        sheet.setColumnWidth(4, 10 * 256);
        sheet.setColumnWidth(5, 21 * 256);
        sheet.setColumnWidth(6, 21 * 256);
        sheet.setColumnWidth(7, 11 * 256);

        // Set columns style
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        // Set alignment
        style.setAlignment(HorizontalAlignment.CENTER);

        // Set colors
        style.setFillBackgroundColor(IndexedColors.BLUE_GREY.getIndex());
        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());

        // Set borders style
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);

        // Set borders colors
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());

        // Set columns title
        row.createCell(0).setCellValue("Id");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Description");
        row.createCell(3).setCellValue("Price");
        row.createCell(4).setCellValue("Quantity");
        row.createCell(5).setCellValue("Supplier");
        row.createCell(6).setCellValue("Date of creation");
        row.createCell(7).setCellValue("Status");

        for (Cell cell : row) {
            cell.setCellStyle(style);
        }
    }
}
