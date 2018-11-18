package crud.controller.controllers;

import crud.controller.services.DaoContactsOpenService;
import crud.controller.services.ViewService;
import crud.model.GenericDao;
import entity.ContractsOpenBuy;

import java.util.List;

public class DaoContractsOpenBuyController implements DaoContactsOpenService,ViewService {

    private final GenericDao dao;

    public DaoContractsOpenBuyController(GenericDao dao) {
        this.dao = dao;
    }

    private ContractsOpenBuy contractsOpenBuy;
    @Override
    public void add(int idSell, int idCustomer, String customerName, String idName, int nrTruck, int nrTruckContract, int amount, String contractName) {
        contractsOpenBuy = new ContractsOpenBuy();

        contractsOpenBuy.setIdSell(idSell);
        contractsOpenBuy.setIdCustomer(idCustomer);
        contractsOpenBuy.setCustomerName(customerName);
        contractsOpenBuy.setIdName(idName);
        contractsOpenBuy.setNrTruck(nrTruck);
        contractsOpenBuy.setNrTruckContract(nrTruckContract);
        contractsOpenBuy.setAmount(amount);
        contractsOpenBuy.setContractName(contractName);
        dao.insert(contractsOpenBuy);
    }

    @Override
    public void update(int id, int idSellAfterChange, int idCustomerAfterChange, String customerNameAfterChange, String idNameAfterChange, int nrTruckAfterChange, int nrTruckContractAfterChange, int amountAfterChange, String contractNameAfterChange) {
        contractsOpenBuy = (ContractsOpenBuy) dao.findById(id);

        contractsOpenBuy.setIdSell(idSellAfterChange);
        contractsOpenBuy.setIdCustomer(idCustomerAfterChange);
        contractsOpenBuy.setCustomerName(customerNameAfterChange);
        contractsOpenBuy.setIdName(idNameAfterChange);
        contractsOpenBuy.setNrTruck(nrTruckAfterChange);
        contractsOpenBuy.setNrTruckContract(nrTruckContractAfterChange);
        contractsOpenBuy.setAmount(amountAfterChange);
        contractsOpenBuy.setContractName(contractNameAfterChange);
        dao.insert(contractsOpenBuy);

    }

    @Override
    public void remove(int id) {
        contractsOpenBuy = (ContractsOpenBuy) dao.findById(id);
        dao.delete(contractsOpenBuy);
    }

    @Override
    public Object findById(int id) {
        contractsOpenBuy = (ContractsOpenBuy) dao.findById(id);
        return contractsOpenBuy;
    }

    @Override
    public void updateRecord(String idOfColumn, String newValue, int idOfRow) {
        contractsOpenBuy = (ContractsOpenBuy) dao.findById(idOfRow);
        dao.query(idOfColumn,newValue,1);
    }

    @Override
    public List<ContractsOpenBuy> selectList() {
        return (List<ContractsOpenBuy>) dao.select();
    }
}
