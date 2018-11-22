import crud.controller.controllers.DaoContractsCloseController;
import crud.controller.controllers.DaoContractsOpenBuyController;
import crud.controller.controllers.DaoContractsOpenSellController;
import crud.model.GenericDaoImpl;
import entity.ContractsClose;
import entity.ContractsOpenBuy;
import entity.ContractsOpenSell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.HibernateUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

public class DaoContracsCloseControllerTest {
    EntityManager entityManager,entityManagerBuy,entityManagerSell;
    DaoContractsOpenSellController daoContractsOpenSellController;
    DaoContractsOpenBuyController daoContractsOpenBuyController;
    DaoContractsCloseController daoContractsCloseController;
    GenericDaoImpl dao,daoFromBuy,daoFromSell;
    ContractsClose contractsClose;
    ContractsOpenSell contractsOpenSell;
    ContractsOpenBuy contractsOpenBuy;

    @Before
    public void before() {
        entityManager = HibernateUtils.getSessionFactory().createEntityManager();

        dao = new GenericDaoImpl(entityManager, ContractsClose.class);
        daoContractsCloseController = new DaoContractsCloseController(dao);

        daoFromBuy = new GenericDaoImpl(entityManagerBuy, ContractsOpenBuy.class);
        daoContractsOpenBuyController = new DaoContractsOpenBuyController(daoFromBuy);

        daoFromSell = new GenericDaoImpl(entityManagerSell, ContractsOpenSell.class);
        daoContractsOpenSellController = new DaoContractsOpenSellController(daoFromSell);
    }

    @After
    public void after() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("Delete  From ContractsClose").executeUpdate();
        entityManager.getTransaction().commit();


        entityManager.getTransaction().begin();
        entityManager.createQuery("Delete  From ContractsOpenBuy").executeUpdate();
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        entityManager.createQuery("Delete  From ContractsOpenSell").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Test
    public void Buy(){
        entityManagerBuy = HibernateUtils.getSessionFactory().createEntityManager();
        contractsOpenBuy = new ContractsOpenBuy();
        contractsOpenBuy.setIdSell(1);
        contractsOpenBuy.setIdCustomer(1);
        contractsOpenBuy.setCustomerName("Test Customer Buy");
        contractsOpenBuy.setIdName("Test Material");
        contractsOpenBuy.setNrTruck(1);
        contractsOpenBuy.setNrTruckContract(1);
        contractsOpenBuy.setAmount(10);
        contractsOpenBuy.setContractName("Test Contract Name");
        contractsOpenBuy.setOpenClose(1);

        entityManagerBuy.getTransaction().begin();
        entityManagerBuy.persist(contractsOpenBuy);
        entityManagerBuy.getTransaction().commit();

        daoFromBuy = new GenericDaoImpl(entityManagerBuy, ContractsOpenBuy.class);

        daoContractsCloseController.CheckStatusTransfer(1,contractsOpenBuy,daoFromBuy);

        List<ContractsClose> addedSelect = dao.select();
        String addedName = addedSelect.get(0).getCustomerName();

        List<ContractsOpenBuy> oldSelect = daoFromBuy.select();

        assertEquals(addedName,"Test Customer Buy");
        assertEquals(0, oldSelect.size());
        assertEquals(addedSelect.get(0).getIdContractBuy(), 1);

    }

    @Test
    public void Sell(){
        entityManagerSell = HibernateUtils.getSessionFactory().createEntityManager();
        contractsOpenSell = new ContractsOpenSell();
        contractsOpenSell.setIdSell(1);
        contractsOpenSell.setIdCustomer(1);
        contractsOpenSell.setCustomerName("Test Customer Sell");
        contractsOpenSell.setIdName("Test Material");
        contractsOpenSell.setNrTruck(1);
        contractsOpenSell.setNrTruckContract(1);
        contractsOpenSell.setAmount(10);
        contractsOpenSell.setContractName("Test Contract Name");
        contractsOpenSell.setOpenClose(1);

        entityManagerSell.getTransaction().begin();
        entityManagerSell.persist(contractsOpenSell);
        entityManagerSell.getTransaction().commit();

        daoFromSell = new GenericDaoImpl(entityManagerSell, ContractsOpenSell.class);
        daoContractsCloseController.CheckStatusTransfer(1,contractsOpenSell,daoFromSell);

        List<ContractsClose> addedSelect = dao.select();
        String addedName = addedSelect.get(0).getCustomerName();

        List<ContractsOpenBuy> oldSelect = daoFromSell.select();

        assertEquals(addedName,"Test Customer Sell");
        assertEquals(0, oldSelect.size());
        assertEquals(addedSelect.get(0).getIdContractSell(), 1);

    }


}
