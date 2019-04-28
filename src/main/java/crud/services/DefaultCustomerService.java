package crud.services;

import crud.repository.CustomerRepository;
import crud.model.Customer;
import crud.services.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DefaultCustomerService implements CustomerService {

    private CustomerRepository customerRepository;

    public DefaultCustomerService(CustomerRepository customerRepository) {
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
    public Customer findById(Long id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        return foundCustomer.orElseGet(() -> Customer.builder().country("").name("").build());
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
