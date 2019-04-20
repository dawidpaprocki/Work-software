package crud.services;

import crud.model.Customer;

import java.util.List;

public interface CustomerService  {
    void remove(Long id);

    Customer findById(Long id);

    List<Customer> findByName(String name);

    List<Customer> selectList();

    void add(Customer customer);

    void update(Long id, String afterChangeName, String afterChangeCountry);

}

