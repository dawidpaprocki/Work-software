import crud.controller.DaoAllTruckController;
import crud.model.GenericDaoImpl;
import entity.AllTruck;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.HibernateUtils;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
public class DaoDaoAllTruckControllerTest {
    EntityManager entityManager;
    DaoAllTruckController daoAllTruckController;
    GenericDaoImpl dao;
    AllTruck allTruck;



    @Before
    public void before() {
        entityManager = HibernateUtils.getSessionFactory().createEntityManager();


        allTruck = new AllTruck();
        allTruck.setDate("13-11-2018");
        allTruck.setMaterial("Cu");
        allTruck.setTruckNumber("WGM 5rc1");
        allTruck.setAmount(10);
        allTruck.setFinalAmount(11);
        allTruck.setSeller("Seller");
        allTruck.setBuyer("buyer");
        allTruck.setTruckNr("1"); // do zmiany na int
        allTruck.setTransportOrder("1/2/2018");
        allTruck.setSalesContractNumber("1/2");
        allTruck.setPurchaseContractNumber("2/2");
        allTruck.setDocumentName("empty");
        allTruck.setColor("red");

        entityManager.getTransaction().begin();
        entityManager.persist(allTruck);
        entityManager.getTransaction().commit();


        dao = new GenericDaoImpl(entityManager, AllTruck.class);
        daoAllTruckController =  DaoAllTruckController.builder()
                .dao(dao).build();

    }

    @After
    public void after() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("Delete  From AllTruck ").executeUpdate();
        entityManager.getTransaction().commit();

    }

    @Test
    public void update(){
        daoAllTruckController.updateRecord("Buyer","Test1",1);
        AllTruck byId =(AllTruck) dao.findById(1);
        assertEquals(byId.getBuyer(),"Test1");

    }

}
