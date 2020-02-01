package jimenez.enger.services;

import jimenez.enger.helpers.ExcelFile;
import jimenez.enger.interfaces.IGenericDao;
import jimenez.enger.models.ProductModel;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductDao implements IGenericDao<ProductModel> {
    int sheetNumber = 0;
    ExcelFile excel = new ExcelFile();

    @Override
    public ProductModel Get(int id) {
        try {
            ProductModel data;

            excel.Open(sheetNumber);
            excel.row = excel.sheet.getRow(id);
            data = getData(id);
            excel.Close();

            return data;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProductModel> GetAll() {
        List<ProductModel> data = new ArrayList<>();

        try {
            excel.Open(sheetNumber);
            Iterator<Row> rowIterator = excel.sheet.rowIterator();

            while (rowIterator.hasNext()) {
                excel.row = rowIterator.next();
                int index = excel.row.getRowNum();
                int lastRow = excel.sheet.getLastRowNum() + 1;

                if (index == 0) {
                    continue;
                }
                if (index == lastRow) {
                    continue;
                }
                data.add(getData(index));
            }
            excel.Close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return data;
    }

    @Override
    public ProductModel Create(ProductModel data) {
        try {
            excel.Open(sheetNumber);

            // Create new row
            int rowNum = excel.sheet.getLastRowNum() + 1;
            excel.row = excel.sheet.createRow(rowNum);
            excel.row.createCell(0).setCellValue(excel.sheet.getLastRowNum());
            excel.row.createCell(1).setCellValue(data.Name);
            excel.row.createCell(2).setCellValue(data.Description);
            excel.row.createCell(3).setCellValue(data.Price);
            excel.row.createCell(4).setCellValue(data.Quantity);
            excel.row.createCell(5).setCellValue(data.Supplier);
            excel.row.createCell(6).setCellValue(data.CreatedDate);
            excel.row.createCell(7).setCellValue(data.Status);
            excel.Close();

//            return Get(excel.row.getRowNum());
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ProductModel Update(int id, ProductModel data) {
        try {
            excel.Open(sheetNumber);
            excel.row = excel.sheet.getRow(id);
            excel.row.getCell(0).setCellValue(id);
            excel.row.getCell(1).setCellValue(data.Name);
            excel.row.getCell(2).setCellValue(data.Description);
            excel.row.getCell(3).setCellValue(data.Price);
            excel.row.getCell(4).setCellValue(data.Quantity);
            excel.row.getCell(5).setCellValue(data.Supplier);
            excel.row.getCell(6).setCellValue(data.CreatedDate);
            excel.row.getCell(7).setCellValue(data.Status);
            excel.Close();

            return Get(excel.row.getRowNum());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int Delete(int id) {
        try {
            excel.Open(sheetNumber);
            int lastRowNum = excel.sheet.getLastRowNum();

            if (id == lastRowNum) {
                Row rowToRemove = excel.sheet.getRow(id);
                if (rowToRemove != null) {
                    excel.sheet.removeRow(rowToRemove);
                }
            }

            if (id < lastRowNum && id >= 1) {
                excel.sheet.shiftRows(id + 1, lastRowNum, -1);
            }

            excel.Close();
            return id;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    private ProductModel getData(int id) {
        try {
            if (id < 1) {
                throw new Exception("Row not found or it is the header row");
            }
            excel.row = excel.sheet.getRow(id);

            int Id = (int) excel.row.getCell(0).getNumericCellValue();
            String Name = excel.row.getCell(1).toString();
            String Description = excel.row.getCell(2).toString();
            Double Price = excel.row.getCell(3).getNumericCellValue();
            int Quantity = (int) excel.row.getCell(4).getNumericCellValue();
            String Supplier = excel.row.getCell(5).toString();
            String CreatedDate = excel.row.getCell(6).toString();
            String Status = excel.row.getCell(7).toString();

            return new ProductModel(Id ,Name, Description, Price, Quantity, Supplier, CreatedDate, Status);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
