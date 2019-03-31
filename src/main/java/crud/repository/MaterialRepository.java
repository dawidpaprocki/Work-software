package crud.repository;

import entity.ContractsOpenSell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<ContractsOpenSell, Long> {

}
