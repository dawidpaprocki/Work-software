package crud.services;

import java.util.List;

public interface DaoService<T> {

    void remove(Long id);

    T findById(Long id);

    List<T> findByName(String name);

    List<T> selectList();
}
