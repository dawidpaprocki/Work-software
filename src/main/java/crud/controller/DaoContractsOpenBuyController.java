package crud.controller;

import crud.services.DaoContactsOpenService;
import crud.services.ViewService;
import crud.model.GenericDao;
import entity.ContractsOpenBuy;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder(toBuilder = true)
public class DaoContractsOpenBuyController implements DaoContactsOpenService,ViewService {

    private int id;
    private int idSell;
    private int idCustomer;
    private String customerName;
    private String MaterialName;
    private int nrTruck;
    private int nrTruckContract;
    private int amount;
    private String contractName;
    private final GenericDao dao;


    private ContractsOpenBuy contractsOpenBuy;
    @Override
    public void add() {
        contractsOpenBuy = new ContractsOpenBuy();

        contractsOpenBuy.setIdSell(this.idSell);
        contractsOpenBuy.setIdCustomer(this.idCustomer);
        contractsOpenBuy.setCustomerName(this.customerName);
        contractsOpenBuy.setMaterialName(this.MaterialName);
        contractsOpenBuy.setNrTruck(this.nrTruck);
        contractsOpenBuy.setNrTruckContract(this.nrTruckContract);
        contractsOpenBuy.setAmount(this.amount);
        contractsOpenBuy.setContractName(this.contractName);
        dao.insert(contractsOpenBuy);
    }

    @Override
    public void update(int id, int idSellAfterChange, int idCustomerAfterChange, String customerNameAfterChange,
                       String MaterialNameAfterChange, int nrTruckAfterChange, int nrTruckContractAfterChange, int amountAfterChange, String contractNameAfterChange) {
        contractsOpenBuy = (ContractsOpenBuy) dao.findById(id);

        contractsOpenBuy.setIdSell(idSellAfterChange);
        contractsOpenBuy.setIdCustomer(idCustomerAfterChange);
        contractsOpenBuy.setCustomerName(customerNameAfterChange);
        contractsOpenBuy.setMaterialName(MaterialNameAfterChange);
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
    public List<ContractsOpenBuy> find(String where, String name) {

        return  (List<ContractsOpenBuy>)  dao.find(where,name);
    }

    @Override
    public void updateRecord(String idOfColumn, String newValue, int idOfRow) {
        contractsOpenBuy = (ContractsOpenBuy) dao.findById(idOfRow);
        dao.query(idOfColumn,newValue,idOfRow);
    }



    @Override
    public List<ContractsOpenBuy> selectList() {
        return (List<ContractsOpenBuy>) dao.select();
    }
}
