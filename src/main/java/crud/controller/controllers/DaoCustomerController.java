package crud.controller.controllers;

import crud.controller.services.DaoCustomerService;
import crud.model.GenericDao;
import entity.Customer;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
@Data
@Builder(toBuilder = true)
public class DaoCustomerController implements DaoCustomerService {
    private int Id;
    private String Name;
    private String Country;

    private final GenericDao dao;

//    public DaoCustomerController(GenericDao dao) {
//        this.dao = dao;
//    }
    private Customer customer;


    @Override
    public void add() {

        customer = new Customer();
        customer.setName(this.Name);
        customer.setCountry(this.Country);
        dao.insert(customer);

    }

    @Override
    public void update(int id, String afterChangeName, String afterChangeCountry) {
        customer  = (Customer) dao.findById(id);
        customer.setName(afterChangeName);
        customer.setCountry(afterChangeCountry);
        dao.update(customer);
    }



    @Override
    public void remove(int id) {
        customer = (Customer) dao.findById(id);
        dao.delete(customer);

    }


    @Override
    public Customer findById(int id) {
        customer = (Customer) dao.findById(id);
        return customer;
    }

    @Override
    public  List<Customer> findByName(String name) {
        List<Customer> list= dao.select();
        list.stream().filter(e->e.getName().equals(name)).collect(Collectors.toList());
        return  list.stream().filter(e->e.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<String> selectList() {
        customer = new Customer();
        List<Customer> select = dao.select();
        return select.stream().map(Customer::getName).collect(Collectors.toList());
    }


}
