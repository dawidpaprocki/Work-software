package crud.repository;

import crud.model.AccessLevel;
import crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessLevelRepository extends JpaRepository<AccessLevel, Long> {
    List<AccessLevel> findAllByUser(User user);
}
