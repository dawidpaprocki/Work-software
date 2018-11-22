import crud.controller.controllers.DaoContractsOpenSellController;
import crud.model.GenericDaoImpl;
import entity.ContractsOpenSell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.HibernateUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class DaoContractsOpenSellTest {
    EntityManager entityManager;
    DaoContractsOpenSellController daoContractsOpenSellController;
    GenericDaoImpl dao;
    ContractsOpenSell contractsOpenSell;

    @Before
    public void before() {
        entityManager = HibernateUtils.getSessionFactory().createEntityManager();
        contractsOpenSell = new ContractsOpenSell();
        contractsOpenSell.setIdSell(1);
        contractsOpenSell.setIdCustomer(1);
        contractsOpenSell.setCustomerName("Test Customer");
        contractsOpenSell.setIdName("Test Material");
        contractsOpenSell.setNrTruck(1);
        contractsOpenSell.setNrTruckContract(1);
        contractsOpenSell.setAmount(10);
        contractsOpenSell.setContractName("Test Contract Name");

        entityManager.getTransaction().begin();
        entityManager.persist(contractsOpenSell);
        entityManager.getTransaction().commit();


        dao = new GenericDaoImpl(entityManager, ContractsOpenSell.class);
        daoContractsOpenSellController = new DaoContractsOpenSellController(dao);
    }

    @After
    public void after() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("Delete  From ContractsOpenSell ").executeUpdate();
        entityManager.getTransaction().commit();

    }

    @Test
    public void addedCustomerTest(){

        daoContractsOpenSellController.add(2,2,"Test1 Customer","Test1 Material",2,2,20,"Test1 Contract Name");
        List<ContractsOpenSell> addedSelect = dao.select();
        int id = addedSelect.get(0).getId();

        assertTrue(addedSelect.size()== 2);
        assertEquals("Test1 Customer", addedSelect.get(1).getCustomerName());
        assertEquals("Test1 Material", addedSelect.get(1).getIdName());
        assertEquals("Test1 Contract Name", addedSelect.get(1).getContractName());
        assertEquals(2, addedSelect.get(1).getIdSell());
        assertEquals(2, addedSelect.get(1).getIdCustomer());
        assertEquals(2, addedSelect.get(1).getNrTruck());
        assertEquals(2, addedSelect.get(1).getNrTruckContract());
        assertEquals(20, addedSelect.get(1).getAmount());

    }

    @Test
    public void updateCustomerTest(){
        List<ContractsOpenSell> addedSelect = dao.select();
        int id = addedSelect.get(0).getId();


        daoContractsOpenSellController.update(id,3,3,"Changed",
                "Changed",3,3,30,"Changed");
        List<ContractsOpenSell> updateSelect = dao.select();
        assertEquals("Changed", updateSelect.get(0).getCustomerName());
        assertEquals("Changed", updateSelect.get(0).getIdName());
        assertEquals("Changed", updateSelect.get(0).getContractName());
        assertEquals(3, updateSelect.get(0).getIdSell());
        assertEquals(3, updateSelect.get(0).getIdCustomer());
        assertEquals(3, updateSelect.get(0).getNrTruck());
        assertEquals(3, updateSelect.get(0).getNrTruckContract());
        assertEquals(30, updateSelect.get(0).getAmount());

    }

    @Test
    public void update(){

        daoContractsOpenSellController.updateRecord("IdName","Test1",1);
        ContractsOpenSell byId =(ContractsOpenSell) dao.findById(1);
        assertEquals(byId.getIdName(),"Test1");

    }


}
