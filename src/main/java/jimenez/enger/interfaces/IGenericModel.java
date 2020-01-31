package jimenez.enger.interfaces;

import java.util.List;

public interface IGenericModel {
  List<String> getFieldsNames();
  String getFieldValue(Object fieldName);
}
