package crud.services;

import entity.ContractsClose;

import java.util.List;
import java.util.Optional;

public interface ContractsCloseService<T> {
    void addUpdateContract(ContractsClose contractsClose);
    void deleteContract(Long id);
    Optional<ContractsClose> findById(Long id);
    List<ContractsClose> findAll();



}
