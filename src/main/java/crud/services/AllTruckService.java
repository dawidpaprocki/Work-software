package crud.services;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AllTruckService<T>{

    void add();
    void remove(int id);
    T findById(int id);
    List<String> selectList();
}
