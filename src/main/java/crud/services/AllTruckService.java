package crud.services;

import entity.AllTruck;

import java.util.List;

public interface AllTruckService{

    void add(AllTruck allTruck);
    void remove(AllTruck objectToDeleteFormDB);
    AllTruck findById(Long id);
    List<AllTruck> selectList();
    void updateRecord(AllTruck allTruck);
}
