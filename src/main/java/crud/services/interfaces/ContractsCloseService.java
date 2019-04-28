package crud.services.interfaces;

import crud.model.ContractsClose;

import java.util.List;
import java.util.Optional;

public interface ContractsCloseService<T> {
    void addUpdate(ContractsClose contractsClose);
    void deleteContract(Long id);
    Optional<ContractsClose> findById(Long id);
    List<ContractsClose> findAll();



}
