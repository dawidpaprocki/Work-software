package crud.services;

import org.springframework.stereotype.Service;

@Service
public interface DaoCustomerService<T> extends DaoService {

    void add();

    void update(int id, String afterChangeName, String afterChangeCountry);

}

