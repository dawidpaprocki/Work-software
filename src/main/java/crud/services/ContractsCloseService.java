package crud.services;

import entity.ContractsClose;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ContractsCloseService<T> {
    void addUpdateContract(ContractsClose contractsClose);
    void deleteContract(Long id);
    Optional<ContractsClose> findById(Long id);
    List<ContractsClose> findAll();



}
