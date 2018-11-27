package crud.controller.services;

import java.util.List;

public interface AllViewService<T>{

    void add();
    void remove(int id);
    T findById(int id);
    List<String> selectList();
}
