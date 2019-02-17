package crud.services;

import java.util.List;

public interface AllTruckService<T>{

    void add();
    void remove(int id);
    T findById(int id);
    List<String> selectList();
}
