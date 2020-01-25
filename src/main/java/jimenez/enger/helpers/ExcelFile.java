package jimenez.enger.helpers;

import jimenez.enger.Config;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

public class ExcelFile {
    public Workbook workbook;
    public Sheet sheet;
    public Row row;
    public Cell cell;
    public String filepath = Config.FILE_PATH + "xcrud_db.xlsx";

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
    public boolean Write() {
        try {
            // Init file
            this.Init(filepath, null);

            // Open file
            FileInputStream fis = new FileInputStream(new File(filepath));
            this.workbook = WorkbookFactory.create(fis);

            // Insert new rows
            // TODO: Refactor
    //        sheet = workbook.createSheet("Productos");
            sheet = workbook.getSheetAt(0);
            sheet.autoSizeColumn(0);
            row = sheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("Hola mundo");
            row.createCell(2).setCellValue("Jimenez");

            // Save changes
            fis.close();

            // Save file
            FileOutputStream fos = new FileOutputStream(filepath);
            workbook.write(fos);
            workbook.close();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean Read() throws  Exception {
        FileInputStream fis = new FileInputStream(new File(filepath));

        // Init file
        this.Init(filepath, fis);

//        int numberOfSheets = workbook.getNumberOfSheets();

//        for (int i = 0; i < numberOfSheets; i++) {
//            sheet = workbook.getSheetAt(i);
            sheet = workbook.getSheetAt(0);

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
//        }

        fis.close();
        return true;
    }
}
