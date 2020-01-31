package jimenez.enger.models;

import jimenez.enger.interfaces.IGenericModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseModel implements IGenericModel {
  Field[] fields = getClass().getFields();
  List<String> fieldsNames = new ArrayList<>();
  Object fieldValue = "";

  public List<String> getFieldsNames() {
    for (Field field : fields) {
      fieldsNames.add(field.getName());
    }
    return fieldsNames;
  }

  public String getFieldValue(Object fieldName) {
      Arrays
        .stream(fields)
        .filter(x -> {
          try {
            fieldValue = x.get(fieldName);
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
          return false;
        }
      );
    return fieldValue.toString();
  }
}
