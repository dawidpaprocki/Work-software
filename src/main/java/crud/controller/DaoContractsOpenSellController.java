package crud.controller;

import crud.services.DaoContactsOpenService;
import crud.services.ViewService;
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
    private String materialName;
    private int nrTruck;
    private int nrTruckContract;
    private int amount;
    private String contractName;
    private final GenericDao dao;

    private ContractsOpenSell contractsOpenSell;

    @Override
    public void add() {
        contractsOpenSell =new  ContractsOpenSell
                .ContractsOpenSellBuilder()
                .idSell(this.idSell)
                .idCustomer(this.idCustomer)
                .customerName(this.customerName)
                .materialName(this.materialName)
                .nrTruck(this.nrTruck)
                .nrTruckContract(this.nrTruckContract)
                .amount(this.amount)
                .contractName(this.contractName)
                .build();
        dao.insert(contractsOpenSell);
    }

    @Override
    public void update(int id, int idSellAfterChange, int idCustomerAfterChange, String customerNameAfterChange,
                       String materialNameAfterChange, int nrTruckAfterChange, int nrTruckContractAfterChange,
                       int amountAfterChange, String contractNameAfterChange) {

        contractsOpenSell = (ContractsOpenSell) dao.findById(id);
        contractsOpenSell = new ContractsOpenSell.ContractsOpenSellBuilder()
                .idSell(idSellAfterChange)
                .idCustomer(idCustomerAfterChange)
                .customerName(customerNameAfterChange)
                .customerName(materialNameAfterChange)
                .nrTruck(nrTruckContractAfterChange)
                .materialName(materialNameAfterChange)
                .nrTruckContract(nrTruckAfterChange)
                .amount(amountAfterChange)
                .contractName(contractNameAfterChange)
                .build();
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
