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
    public int Status;
}
