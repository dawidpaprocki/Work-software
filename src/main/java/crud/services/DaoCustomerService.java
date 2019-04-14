package crud.services;

import entity.Customer;

public interface DaoCustomerService<T> extends DaoService {

    void add(Customer customer);

    void update(Long id, String afterChangeName, String afterChangeCountry);

}

