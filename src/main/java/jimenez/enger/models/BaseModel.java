package jimenez.enger.models;

import jimenez.enger.interfaces.IGenericModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseModel implements IGenericModel {
  Field[] fields = getClass().getFields();
  List<String> fieldsNames = new ArrayList<>();

  public List<String> getFieldsNames() {
    for (Field field : fields) {
      fieldsNames.add(field.getName());
    }
    return fieldsNames;
  }
}
