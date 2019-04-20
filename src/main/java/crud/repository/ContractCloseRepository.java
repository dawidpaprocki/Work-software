package crud.repository;

import crud.model.ContractsClose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractCloseRepository extends JpaRepository<ContractsClose, Long> {

}
