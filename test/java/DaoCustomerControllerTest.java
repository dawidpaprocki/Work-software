import crud.controller.DaoCustomerController;
import crud.model.GenericDaoImpl;
import entity.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.HibernateUtils;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DaoCustomerControllerTest {
    EntityManager entityManager;
    DaoCustomerController daoCustomerController;
    GenericDaoImpl dao;
    Customer customer;

    @Before
    public void before() {
        entityManager = HibernateUtils.getSessionFactory().createEntityManager();
        customer = new Customer();
        customer.setName("Test1");
        customer.setCountry("Poland");

        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();


        dao = new GenericDaoImpl(entityManager, Customer.class);
        daoCustomerController = DaoCustomerController.builder().dao(dao).build();
    }

    @After
    public void after() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("Delete  From Customer ").executeUpdate();
        entityManager.getTransaction().commit();

    }

    public int getCustomerId() {
        Customer o = (Customer) dao.select().get(0);
        return o.getId();
    }

    @Test
    public void addedCustomerTestTrue() {

        // given
        // when

        daoCustomerController = DaoCustomerController.builder()
                .Name("Test2")
                .Country("Poland")
                .dao(dao)
                .build();
        daoCustomerController.add();

        List<Customer> select = dao.select();
        List<String> collectName = select.stream().map(Customer::getName).collect(Collectors.toList());
        List<String> collectCountry = select.stream().map(Customer::getCountry).collect(Collectors.toList());
        // then
        assertTrue(collectName.contains("Test2"));
        assertTrue(collectCountry.contains("Poland"));
        assertEquals(dao.select().size(), 2);
    }

    @Test
    public void addedCustomerTestFalse() {

        // given
        // when
        daoCustomerController = DaoCustomerController.builder()
                .Name("Test2")
                .Country("Poland")
                .dao(dao)
                .build();
        daoCustomerController.add();
        List<Customer> select = dao.select();
        List<String> collectName = select.stream().map(Customer::getName).collect(Collectors.toList());
        List<String> collectCountry = select.stream().map(Customer::getCountry).collect(Collectors.toList());
        // then
        assertNotEquals(dao.select().size(), 1);
        assertFalse(collectName.contains(""));
        assertFalse(collectCountry.contains(""));
    }

    @Test
    public void updateCustomerTestTrue() {

        // given
        int id = getCustomerId();
        // when
        daoCustomerController.update(id, "TestAfterChange", "CountryAfterChange");
        List<Customer> select = dao.select();
        List<String> collectName = select.stream().map(Customer::getName).collect(Collectors.toList());
        List<String> collectCountry = select.stream().map(Customer::getCountry).collect(Collectors.toList());
        // then
        assertTrue(collectName.contains("TestAfterChange"));
        assertTrue(collectCountry.contains("CountryAfterChange"));
    }

    @Test
    public void updateCustomerTestFalse() {

        // given
        int id = getCustomerId();
        // when
        daoCustomerController.update(id, "TestAfterChange", "CountryAfterChange");
        List<Customer> select = dao.select();
        List<String> collectName = select.stream().map(Customer::getName).collect(Collectors.toList());
        List<String> collectCountry = select.stream().map(Customer::getCountry).collect(Collectors.toList());
        // then
        assertFalse(collectName.contains("Test1"));
        assertFalse(collectCountry.contains("Poland"));
    }

//    @Test
//    public void removeCustomerTestTrue() {
//
//        // given
//        int id = getCustomerId();
//        // when
//        daoCustomerController.remove(id);
//        List<Customer> select = dao.select();
//        // then
//        assertEquals(0, select.size());
//
//    }
//
//
//    @Test
//    public void removeCustomerTestFalse() {
//
//        // given
//        int id = getCustomerId();
//        // when
//        daoCustomerController.remove(id);
//        List<Customer> select = dao.select();
//        // then
//        assertFalse(select.size() > 0);
//
//    }
//
//
//    @Test
//    public void findByIdTestTrue() {
//
//        // given
//        int id = getCustomerId();
//        // when
//        customer = daoCustomerController.findById(id);
//        // then
//        assertEquals(customer.getId(), id);
//
//    }
//
//    @Test
//    public void findByIdTestFalse() {
//
//        // given
//        int id = getCustomerId();
//        // when
//        customer = daoCustomerController.findById(id);
//        // then
//        assertNotEquals(customer.getId(), id + 1);
//
//    }
//
//    @Test
//    public void findByNameTestTrue() {
//
//        // given
//        String name = customer.getName();
//        // when
//        List<Customer> customers = daoCustomerController.find("Test1");
//        // then
//        assertEquals(customers.get(0).getName(), name);
//
//    }
//
//    @Test
//    public void findByNameTestFalse() {
//        // given
//        // when
//        List<Customer> customers = daoCustomerController.find("Test1");
//        // then
//        assertNotEquals(customers.get(0).getName(), "Test2");
//
//    }

}
