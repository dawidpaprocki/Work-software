package crud.services.interfaces;

import crud.model.AllTruck;

import java.util.List;

public interface AllTruckService{

    void addOrUpdate(AllTruck allTruck);
    void remove(AllTruck objectToDeleteFormDB);
    AllTruck findById(Long id);
    List<AllTruck> selectList();
}
