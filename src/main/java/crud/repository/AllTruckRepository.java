package crud.repository;

import crud.model.AllTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllTruckRepository extends JpaRepository<AllTruck, Long> {
//    @Query("UPDATE allTruck SET columnName = newValue WHERE id = idOfColumn")
//    void updateRecord(@Param("columnName")String columnName,@Param("newValue")String newValue,@Param("idOfColumn")Long idOfColumn);
}
