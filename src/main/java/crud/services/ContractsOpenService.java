package crud.services;

import java.util.List;

public interface ContractsOpenService<T>   {

    void addOrUpdate(T t);
    List<T> selectList();
    void remove(Long id);
    T findById(Long id);
    T findByName(String name);
    String getMaterialName(Long contractId);
}
