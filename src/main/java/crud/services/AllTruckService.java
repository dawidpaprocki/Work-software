package crud.services;

import crud.model.AllTruck;

import java.util.List;

public interface AllTruckService{

    void addOrUpdate(AllTruck allTruck);
    void remove(AllTruck objectToDeleteFormDB);
    AllTruck findById(Long id);
    List<AllTruck> selectList();
    void updateRecord(String columnName, String newValue, Long idRow);
}
