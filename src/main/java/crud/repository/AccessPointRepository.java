package crud.repository;

import crud.model.AccessPoint;
import crud.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessPointRepository extends JpaRepository<AccessPoint, Long> {
   Optional<AccessPoint> findByRoleAndPointName(Role role, String pointName);
    List<AccessPoint> findByRole(Role role);
}
