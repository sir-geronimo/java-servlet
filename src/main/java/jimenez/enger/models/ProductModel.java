package jimenez.enger.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductModel extends BaseModel {
  public int Id;
  public String Name;
  public String Description;
  public double Price;
  public int Quantity;
  public String Supplier;
  public String CreatedDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
  public String Status;

  public ProductModel(int id, String name, String description, Double price, int quantity, String supplier, String createdDate, String status) {
    Id = id;
    Name = name;
    Description = description;
    Price = price;
    Quantity = quantity;
    Supplier = supplier;
    CreatedDate = createdDate;
    Status = status;
  }


  public ProductModel(int id, String name, String description, Double price, int quantity, String supplier, String status) {
    Id = id;
    Name = name;
    Description = description;
    Price = price;
    Quantity = quantity;
    Supplier = supplier;
    Status = status;
  }

  public ProductModel() {

  }

  public int getId() {
      return Id;
  }

  public String getName() {
      return Name;
  }

  public String getDescription() {
      return Description;
  }

  public double getPrice() {
      return Price;
  }

  public int getQuantity() {
      return Quantity;
  }

  public String getSupplier() {
      return Supplier;
  }

  public String getCreatedDate() {
      return CreatedDate;
  }

  public String getStatus() {
        return Status;
    }
}
