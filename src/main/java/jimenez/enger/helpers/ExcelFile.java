package jimenez.enger.helpers;

import jimenez.enger.Config;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Iterator;

public class ExcelFile<T> {
    public File file;
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
    public File OpenFile() throws Exception{
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

        return file;
    }
    public boolean Write(T data, int sheetNumber) {
        try {
            // Init file
            this.Init(filepath, null);

            // Open file
            FileInputStream fis = new FileInputStream(OpenFile());
            this.workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheetAt(sheetNumber);
            sheet.autoSizeColumn(0);

            // Insert new rows
            // TODO: Refactor to write generic data
            // row.createCell(0).setCellValue("Hola mundo");

            Field[] fields = data.getClass().getFields();
            int i = 0;

            for (Field field : fields) {
                /* TODO: Fix error:

                //  java.lang.IllegalArgumentException: Can not set int field jimenez.enger.models.ProductModel.Id to java.lang.String
                //	at sun.reflect.UnsafeFieldAccessorImpl.throwSetIllegalArgumentException(UnsafeFieldAccessorImpl.java:167)
                //	at sun.reflect.UnsafeFieldAccessorImpl.throwSetIllegalArgumentException(UnsafeFieldAccessorImpl.java:171)
                //	at sun.reflect.UnsafeFieldAccessorImpl.ensureObj(UnsafeFieldAccessorImpl.java:58)
                //	at sun.reflect.UnsafeIntegerFieldAccessorImpl.getInt(UnsafeIntegerFieldAccessorImpl.java:56)
                //	at sun.reflect.UnsafeIntegerFieldAccessorImpl.get(UnsafeIntegerFieldAccessorImpl.java:36)
                //	at java.lang.reflect.Field.get(Field.java:393)
                //	at jimenez.enger.helpers.ExcelFile.Write(ExcelFile.java:82)
                //	at jimenez.enger.services.ProductDao.Create(ProductDao.java:35)
                //	at jimenez.enger.controllers.ProductController.doPost(ProductController.java:21)
                // Get field name

                 */
                Object fieldName = field.getName();

                // Get field value
                Object fieldValue = field.get(fieldName);

                row.createCell(i).setCellValue(fieldValue.toString());
                i++;
            }

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
        FileInputStream fis = new FileInputStream(OpenFile());

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
