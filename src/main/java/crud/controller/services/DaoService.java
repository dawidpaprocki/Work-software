package crud.controller.services;

import java.util.List;

public interface DaoService<T> {

    void remove(int id);

    T findById(int id);

    List<T> findByName(String name);

    List<T> selectList();
}
