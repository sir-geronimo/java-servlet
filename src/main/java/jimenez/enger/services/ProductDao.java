package jimenez.enger.services;

import jimenez.enger.helpers.ExcelFile;
import jimenez.enger.interfaces.IGenericDao;
import jimenez.enger.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IGenericDao<ProductModel> {
    int sheetNumber = 0;
    ProductModel productModel = null;
    ExcelFile excel = new ExcelFile();

    @Override
    public ProductModel Get(int id) {
        try {
            return productModel;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProductModel> GetAll() {
        List<ProductModel> productModels = new ArrayList<>();

        return productModels;
    }

    @Override
    public ProductModel Create(ProductModel data) {
        try {
            List<String> fields = data.getFieldsNames();

            excel.Open();
            // TODO: Check this for
//            for (int i = 0; i < fields.size(); i++) {
//                String value = data.getFieldValue(data);
//                excel.Write(value, sheetNumber, i);
//            }

            excel.Write(data.Name, sheetNumber, 0);
            excel.Write(data.Description, sheetNumber, 1);
            excel.Write(Double.toString(data.Price), sheetNumber, 2);
            excel.Write(Integer.toString(data.Quantity), sheetNumber, 3);
            excel.Save();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ProductModel Update(int id, ProductModel data) {
        return null;
    }

    @Override
    public int Delete(int id) {
        return 0;
    }
}
