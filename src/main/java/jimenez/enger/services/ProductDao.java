package jimenez.enger.services;

import jimenez.enger.helpers.ExcelFile;
import jimenez.enger.interfaces.GenericDao;
import jimenez.enger.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductDao implements GenericDao<ProductModel> {
    int sheetNumber = 0;
    ProductModel productModel = null;
    ExcelFile<ProductModel> excel = new ExcelFile<>();

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
            excel.Write(data, sheetNumber);
            return Get(1);
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
