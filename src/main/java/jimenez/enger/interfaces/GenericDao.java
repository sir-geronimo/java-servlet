package jimenez.enger.interfaces;

import java.util.List;

public interface GenericDao<T> {
    T Get(int id);
    List<T> GetAll();
    T Create(T data);
    T Update(int id, T data);
    int Delete(int id);
}
