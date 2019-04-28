package crud.services.interfaces;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ViewService<T> {

    void updateRecord(T t);
    List<T> findAll();
}
