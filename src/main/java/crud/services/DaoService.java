package crud.services;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DaoService<T> {

    void remove(int id);

    T findById(int id);

    List<T> findByName(String name);

    List<T> selectList();
}
