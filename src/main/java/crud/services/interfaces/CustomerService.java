package crud.services.interfaces;

import crud.model.Customer;

import java.util.List;

public interface CustomerService  {
    void remove(Customer customer);

    Customer findById(Long id);

    Customer findByName(String name);

    List<Customer> selectList();

    void add(Customer customer);

    void update(Customer customer);

}

