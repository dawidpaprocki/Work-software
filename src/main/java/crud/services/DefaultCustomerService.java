package crud.services;

import crud.model.Customer;
import crud.repository.CustomerRepository;
import crud.services.interfaces.CustomerService;
import org.springframework.stereotype.Service;
import tools.PropertiesReader;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultCustomerService implements CustomerService {

    private CustomerRepository customerRepository;

    private PropertiesReader propertiesReader;

    public DefaultCustomerService(CustomerRepository customerRepository, PropertiesReader propertiesReader) {
        this.customerRepository = customerRepository;
        this.propertiesReader = propertiesReader;
    }

    @Override
    public void add(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        return foundCustomer.orElseGet(() -> Customer.builder()
                .name(propertiesReader.getPropertiesFile().getProperty("lackOfCustomer"))
                .build());
    }

    @Override
    public Customer findByName(String name) {
        return customerRepository.findByName(name).orElse(
                Customer.builder()
                        .name(propertiesReader.getPropertiesFile().getProperty("lackOfCustomer"))
                        .build()
        );
    }

    @Override
    public List selectList() {
        return customerRepository.findAll();
    }
}
