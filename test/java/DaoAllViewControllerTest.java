import crud.controller.controllers.AllViewController;
import crud.model.GenericDaoImpl;
import entity.AllView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.HibernateUtils;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
public class DaoAllViewControllerTest {
    EntityManager entityManager;
    AllViewController allViewController;
    GenericDaoImpl dao;
    AllView allView;

    @Before
    public void before() {
        entityManager = HibernateUtils.getSessionFactory().createEntityManager();
        allView = new AllView();
        allView.setData("13-11-2018");
        allView.setMaterial("Cu");
        allView.setTruck("WGM 5rc1");
        allView.setAmount(10);
        allView.setFinalAmount(11);
        allView.setFroms("Seller");
        allView.setTos("buyer");
        allView.setTruckNr("1"); // do zmiany na int
        allView.setTransportOrder("1/2/2018");
        allView.setVk("1/2");
        allView.setEk("2/2");
        allView.setAmsDoc("empty");
        allView.setColor("red");

        entityManager.getTransaction().begin();
        entityManager.persist(allView);
        entityManager.getTransaction().commit();


        dao = new GenericDaoImpl(entityManager, AllView.class);
        allViewController = new AllViewController(dao);
    }

    @After
    public void after() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("Delete  From AllView ").executeUpdate();
        entityManager.getTransaction().commit();

    }

    @Test
    public void update(){

        allViewController.updateRecord("Tos","Test1",1);
        AllView byId =(AllView) dao.findById(1);
        assertEquals(byId.getTos(),"Test1");

    }

}
