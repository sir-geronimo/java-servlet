package jimenez.enger.services;

import jimenez.enger.helpers.ExcelFile;
import jimenez.enger.interfaces.CrudAction;
import jimenez.enger.models.Product;

import java.util.List;

public class ProductDao implements CrudAction<Product> {
    Product product = null;
    ExcelFile excel = new ExcelFile();

    @Override
    public Product Get(int id) {
        try {
            return product;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> GetAll() {
        return null;
    }

    @Override
    public Product Create(Product data) {
        try {
            excel.Write(data, 1);
            return Get(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Product Update(int id, Product data) {
        return null;
    }

    @Override
    public int Delete(int id) {
        return 0;
    }
}
