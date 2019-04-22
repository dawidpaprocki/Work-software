package crud.services;

import crud.model.AllTruck;
import crud.repository.AllTruckRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultAllTruckService implements AllTruckService {
    private AllTruckRepository allTruckRepository;
    public DefaultAllTruckService(AllTruckRepository allTruckRepository) {
        this.allTruckRepository = allTruckRepository;
    }
    @Override
    public void addOrUpdate(AllTruck truckToAdd) {
        allTruckRepository.save(truckToAdd);
    }

    @Override
    public void remove(AllTruck objectToDeleteFormDB) {
        allTruckRepository.delete(objectToDeleteFormDB);
    }

    @Override
    public AllTruck findById(Long id) {
        Optional<AllTruck> foundTruck = allTruckRepository.findById(id);
        return foundTruck.orElseGet(() -> AllTruck.builder().build());
    }

    @Override
    public List<AllTruck> selectList() {
        return allTruckRepository.findAll();
    }




}
