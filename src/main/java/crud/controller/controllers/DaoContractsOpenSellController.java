package crud.controller.controllers;

import crud.controller.services.DaoContactsOpenService;
import crud.controller.services.ViewService;
import crud.model.GenericDao;
import entity.ContractsOpenSell;

import java.util.List;

public class DaoContractsOpenSellController implements DaoContactsOpenService, ViewService {
    private final GenericDao dao;

    public DaoContractsOpenSellController(GenericDao dao) {
        this.dao = dao;
    }

    private ContractsOpenSell contractsOpenSell;

    //should be builder
    @Override
    public void add(int idSell, int idCustomer, String customerName, String idName, int nrTruck, int nrTruckContract, int amount, String contractName) {
        contractsOpenSell = new ContractsOpenSell();

        contractsOpenSell.setIdSell(idSell);
        contractsOpenSell.setIdCustomer(idCustomer);
        contractsOpenSell.setCustomerName(customerName);
        contractsOpenSell.setIdName(idName);
        contractsOpenSell.setNrTruck(nrTruck);
        contractsOpenSell.setNrTruckContract(nrTruckContract);
        contractsOpenSell.setAmount(amount);
        contractsOpenSell.setContractName(contractName);
        dao.insert(contractsOpenSell);
    }
    //should be builder
    @Override
    public void update(int id, int idSellAfterChange, int idCustomerAfterChange, String customerNameAfterChange,
                       String idNameAfterChange, int nrTruckAfterChange, int nrTruckContractAfterChange,
                       int amountAfterChange, String contractNameAfterChange) {

        contractsOpenSell = (ContractsOpenSell) dao.findById(id);

        contractsOpenSell.setIdSell(idSellAfterChange);
        contractsOpenSell.setIdCustomer(idCustomerAfterChange);
        contractsOpenSell.setCustomerName(customerNameAfterChange);
        contractsOpenSell.setIdName(idNameAfterChange);
        contractsOpenSell.setNrTruck(nrTruckAfterChange);
        contractsOpenSell.setNrTruckContract(nrTruckContractAfterChange);
        contractsOpenSell.setAmount(amountAfterChange);
        contractsOpenSell.setContractName(contractNameAfterChange);
        dao.update(contractsOpenSell);


    }

    @Override
    public void remove(int id) {
        contractsOpenSell = (ContractsOpenSell) dao.findById(id);
        dao.delete(contractsOpenSell);
    }

    @Override
    public ContractsOpenSell findById(int id) {
        contractsOpenSell = (ContractsOpenSell) dao.findById(id);
        return contractsOpenSell;
    }


    @Override
    public void updateRecord(String idOfColumn, String newValue, int idOfRow) {
        contractsOpenSell = (ContractsOpenSell) dao.findById(idOfRow);
        dao.query(idOfColumn,newValue,1);
    }

    @Override
    public List<ContractsOpenSell> selectList() {
        return (List<ContractsOpenSell>) dao.select();
    }


}
