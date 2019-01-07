package crud.controller.controllers;

import crud.controller.services.ContactsCloseService;
import crud.controller.services.DaoContactsOpenService;
import crud.controller.services.ViewService;
import crud.model.GenericDao;
import entity.ContractsClose;
import entity.ContractsOpenBuy;
import entity.ContractsOpenSell;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder(toBuilder = true)
public class DaoContractsCloseController implements DaoContactsOpenService, ViewService, ContactsCloseService {
    private int id;
    private int idSell;
    private int idCustomer;
    private String customerName;
    private String idName;
    private int nrTruck;
    private int nrTruckContract;
    private int amount;
    private String contractName;
    private int openClose;
    private int idContractBuy;
    private int idContractSell;

    private final GenericDao dao;

    private ContractsClose contractsClose;

    @Override
    public void add() {
        contractsClose = new ContractsClose();

        contractsClose.setIdSell(this.idSell);
        contractsClose.setIdCustomer(this.idCustomer);
        contractsClose.setCustomerName(this.customerName);
        contractsClose.setIdName(this.idName);
        contractsClose.setNrTruck(this.nrTruck);
        contractsClose.setNrTruckContract(this.nrTruckContract);
        contractsClose.setAmount(this.amount);
        contractsClose.setContractName(this.contractName);
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
    public List<ContractsClose> find( String where, String name) {

        return  (List<ContractsClose>)  dao.find(where,name);
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
    public void CheckStatusTransfer(int id, Class tableFormGet, GenericDao daoFrom) {
        //Need to make refactor this code to avoid duplicate

        if (tableFormGet.getSimpleName().equals("ContractsOpenBuy")) {
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
        } else if (tableFormGet.getSimpleName().equals("ContractsOpenSell")) {
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
