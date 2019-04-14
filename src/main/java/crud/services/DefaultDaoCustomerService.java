package crud.services;

import crud.repository.CustomerRepository;
import entity.Customer;

import java.util.List;
import java.util.Optional;

public class DefaultDaoCustomerService<T> implements DaoCustomerService {

    private CustomerRepository customerRepository;

    public DefaultDaoCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void add(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void update(Long id, String afterChangeName, String afterChangeCountry) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        foundCustomer.ifPresent(customer -> customerRepository.save(customer));
    }

    @Override
    public void remove(Long id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        foundCustomer.ifPresent(customer -> customerRepository.delete(customer));
    }

    @Override
    public Object findById(Long id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if (foundCustomer.isPresent()) {
            return foundCustomer;
        }
        return Customer.builder().country("").name("").build();
    }

    @Override
    public List findByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public List selectList() {
        return customerRepository.findAll();
    }
}
