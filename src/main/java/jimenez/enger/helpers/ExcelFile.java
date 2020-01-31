package jimenez.enger.helpers;

import jimenez.enger.Config;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

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
    public void Open() throws Exception {
        // Init file
        Init(filepath, null);

        file = new File(filepath);

        if (!file.exists()) {
            // Initiate sheets
            workbook.createSheet("Dashboard");
            workbook.createSheet("Products");
            workbook.createSheet("Users");

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
    }
    public void Write(String data, int sheetNumber, int index) {
        try {
            sheet = workbook.getSheetAt(sheetNumber);
            sheet.autoSizeColumn(index);
            int rowNum = sheet.getLastRowNum() + 1;
            row = sheet.createRow(rowNum);

            // Insert new rows
            // TODO: Refactor to write generic data
             row.createCell(index).setCellValue(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean Read(int sheetNumber) {
        try {
            // Init file
            this.Init(filepath, fis);
            sheet = workbook.getSheetAt(sheetNumber);

            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                row = rowIterator.next();
                int index = row.getRowNum() + 1;

                String name = sheet.getRow(index).getCell(0).toString();
                String price = sheet.getRow(index).getCell(1).toString();

//                Iterator<Cell> cellIterator = row.cellIterator();
//                while (cellIterator.hasNext()) {
//                    cell = cellIterator.next();
//                }
            }

            fis.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public void Save() throws Exception {
        // Save changes
        fis.close();

        // Save file
        FileOutputStream fos = new FileOutputStream(filepath);
        workbook.write(fos);
        workbook.close();
        fos.close();
    }
}
