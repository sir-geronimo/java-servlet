package jimenez.enger.helpers;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class ReadFromExcel {
    public String _filename;
    public Workbook workbook;
    public Sheet sheet;
    public Row row;
    public Cell cell;

    public void Read(String filename)
            throws Exception {
        this._filename = filename;

        FileInputStream fis = new FileInputStream(new File(this._filename));

        if (this._filename.endsWith("xlsx")) {
            this.workbook = new XSSFWorkbook(fis);
        } else if (this._filename.endsWith("xls")) {
            this.workbook = new HSSFWorkbook(fis);
        } else {
            throw new Exception("Invalid file name, must be xls or xlsx");
        }

        int numberOfSheets = workbook.getNumberOfSheets();

        for (int i = 0; i < numberOfSheets; i++) {
            sheet = workbook.getSheetAt(i);

            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                }
            }
        }

        fis.close();
    }
}
