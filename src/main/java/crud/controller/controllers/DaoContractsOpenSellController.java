package crud.controller.controllers;

import crud.controller.services.DaoContactsOpenService;
import crud.controller.services.ViewService;
import crud.model.GenericDao;
import entity.ContractsOpenSell;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder(toBuilder = true)
public class DaoContractsOpenSellController implements DaoContactsOpenService, ViewService {

    private int id;
    private int idSell;
    private int idCustomer;
    private String customerName;
    private String idName;
    private int nrTruck;
    private int nrTruckContract;
    private int amount;
    private String contractName;
    private final GenericDao dao;

//    public DaoContractsOpenSellController(GenericDao dao) {
//        this.dao = dao;
//    }

    private ContractsOpenSell contractsOpenSell;

    //should be builder
    @Override
    public void add() {
        contractsOpenSell = new ContractsOpenSell();

        contractsOpenSell.setIdSell(this.idSell);
        contractsOpenSell.setIdCustomer(this.idCustomer);
        contractsOpenSell.setCustomerName(this.customerName);
        contractsOpenSell.setIdName(this.idName);
        contractsOpenSell.setNrTruck(this.nrTruck);
        contractsOpenSell.setNrTruckContract(this.nrTruckContract);
        contractsOpenSell.setAmount(this.amount);
        contractsOpenSell.setContractName(this.contractName);
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
    public List<ContractsOpenSell> find( String where, String name) {

        return  (List<ContractsOpenSell>)  dao.find(where,name);
    }


    @Override
    public void updateRecord(String idOfColumn, String newValue, int idOfRow) {
        contractsOpenSell = (ContractsOpenSell) dao.findById(idOfRow);
        dao.query(idOfColumn,newValue,idOfRow);
    }

    @Override
    public List<ContractsOpenSell> selectList() {
        return (List<ContractsOpenSell>) dao.select();
    }


}
