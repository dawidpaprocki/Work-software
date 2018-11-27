package crud.model;

import java.util.List;

public interface GenericDao<T> {
    void insert(T obj);
    void update(T obj);
    void delete(T obj);

    T findById(int id);
    List<T> find( String where, String name );

    List<T> select();
    void query(String query, String newValue, int idRow);

}
