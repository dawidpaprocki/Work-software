package crud.controller.controllers;

import crud.controller.services.ContactsCloseService;
import crud.controller.services.DaoContactsOpenService;
import crud.controller.services.ViewService;
import crud.model.GenericDao;
import entity.ContractsClose;
import entity.ContractsOpenBuy;
import entity.ContractsOpenSell;

import java.util.List;

public class DaoContractsCloseController implements DaoContactsOpenService, ViewService, ContactsCloseService {

    private final GenericDao dao;

    public DaoContractsCloseController(GenericDao dao) {
        this.dao = dao;
    }

    private ContractsClose contractsClose;

    @Override
    public void add(int idSell, int idCustomer, String customerName, String idName, int nrTruck, int nrTruckContract, int amount, String contractName) {
        contractsClose = new ContractsClose();

        contractsClose.setIdSell(idSell);
        contractsClose.setIdCustomer(idCustomer);
        contractsClose.setCustomerName(customerName);
        contractsClose.setIdName(idName);
        contractsClose.setNrTruck(nrTruck);
        contractsClose.setNrTruckContract(nrTruckContract);
        contractsClose.setAmount(amount);
        contractsClose.setContractName(contractName);
        dao.insert(contractsClose);
    }

    @Override
    public void update(int id, int idSellAfterChange, int idCustomerAfterChange, String customerNameAfterChange, String idNameAfterChange, int nrTruckAfterChange, int nrTruckContractAfterChange, int amountAfterChange, String contractNameAfterChange) {
        contractsClose = (ContractsClose) dao.findById(id);

        contractsClose.setIdSell(idSellAfterChange);
        contractsClose.setIdCustomer(idCustomerAfterChange);
        contractsClose.setCustomerName(customerNameAfterChange);
        contractsClose.setIdName(idNameAfterChange);
        contractsClose.setNrTruck(nrTruckAfterChange);
        contractsClose.setNrTruckContract(nrTruckContractAfterChange);
        contractsClose.setAmount(amountAfterChange);
        contractsClose.setContractName(contractNameAfterChange);
        dao.update(contractsClose);

    }

    @Override
    public void remove(int id) {
        contractsClose = (ContractsClose) dao.findById(id);
        dao.delete(contractsClose);
    }

    @Override
    public Object findById(int id) {
        contractsClose = (ContractsClose) dao.findById(id);
        return contractsClose;
    }

    @Override
    public void updateRecord(String idOfColumn, String newValue, int idOfRow) {
        contractsClose = (ContractsClose) dao.findById(idOfRow);
        dao.query(idOfColumn, newValue, 1);
    }

    @Override
    public List<ContractsClose> selectList() {
        return (List<ContractsClose>)dao.select();
    }


    @Override
    public void CheckStatusTransfer(int id, Object tableFormGet, GenericDao daoFrom) {
        //Need to make refactor this code to avoid duplicate

        if (tableFormGet.getClass().getSimpleName().equals("ContractsOpenBuy")) {
            ContractsOpenBuy contractsOpenBuy = (ContractsOpenBuy) daoFrom.findById(id);

            if (contractsOpenBuy.getOpenClose() == 1) {
                contractsClose = new ContractsClose();

                contractsClose.setIdSell(contractsOpenBuy.getIdSell());
                contractsClose.setIdCustomer(contractsOpenBuy.getIdCustomer());
                contractsClose.setCustomerName(contractsOpenBuy.getCustomerName());
                contractsClose.setIdName(contractsOpenBuy.getIdName());
                contractsClose.setNrTruck(contractsOpenBuy.getNrTruck());
                contractsClose.setNrTruckContract(contractsOpenBuy.getNrTruckContract());
                contractsClose.setAmount(contractsOpenBuy.getAmount());
                contractsClose.setContractName(contractsOpenBuy.getCustomerName());
                contractsClose.setIdContractBuy(contractsOpenBuy.getId());
                dao.insert(contractsClose);
                daoFrom.delete(contractsOpenBuy);
            }
        } else if (tableFormGet.getClass().getSimpleName().equals("ContractsOpenSell")) {
            ContractsOpenSell contractsOpenSell = (ContractsOpenSell) daoFrom.findById(id);

            if (contractsOpenSell.getOpenClose() == 1) {
                contractsClose = new ContractsClose();

                contractsClose.setIdSell(contractsOpenSell.getIdSell());
                contractsClose.setIdCustomer(contractsOpenSell.getIdCustomer());
                contractsClose.setCustomerName(contractsOpenSell.getCustomerName());
                contractsClose.setIdName(contractsOpenSell.getIdName());
                contractsClose.setNrTruck(contractsOpenSell.getNrTruck());
                contractsClose.setNrTruckContract(contractsOpenSell.getNrTruckContract());
                contractsClose.setAmount(contractsOpenSell.getAmount());
                contractsClose.setContractName(contractsOpenSell.getCustomerName());
                contractsClose.setIdContractSell(contractsOpenSell.getId());
                dao.insert(contractsClose);
                daoFrom.delete(contractsOpenSell);
            }


        }
    }
}
