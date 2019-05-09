package crud.services.interfaces;

import java.util.List;

public interface ContractsOpenService<T>   {

    void addOrUpdate(T t);
    List<T> selectList();
    void remove(T ContractOpen);
    T findById(Long id);
    T findByContractNumber(String name);
}
