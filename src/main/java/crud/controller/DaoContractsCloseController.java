package crud.controller;

import crud.model.GenericDao;
import crud.services.DaoContactsOpenService;
import crud.services.DefaultContractsCloseService;
import crud.services.ViewService;
import entity.ContractsClose;
import entity.ContractsOpenBuy;
import entity.ContractsOpenSell;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@Builder(toBuilder = true)
public class DaoContractsCloseController implements DaoContactsOpenService, ViewService {

    private int id;
    private int idSell;
    private int idCustomer;
    private String customerName;
    private String materialName;
    private int nrTruck;
    private int nrTruckContract;
    private int amount;
    private String contractName;
    private int openClose;
    private int idContractBuy;
    private int idContractSell;

    private final GenericDao dao;
    private final DefaultContractsCloseService defaultContractsCloseService;


    private ContractsClose contractsClose;

    @Override
    public void add() {

        contractsClose = new ContractsClose
                .ContractsCloseBuilder()
                .idSell(this.idSell)
                .idCustomer(this.idCustomer)
                .customerName(this.customerName)
                .materialName(this.materialName)
                .nrTruck(this.nrTruck)
                .nrTruckContract(this.nrTruckContract)
                .amount(this.amount)
                .contractName(this.contractName)
                .build();
        defaultContractsCloseService.addUpdateContract(contractsClose);
    }



    @Override
    public void update(int idSellAfterChange, int idCustomerAfterChange, String customerNameAfterChange, String materialNameAfterChange, int nrTruckAfterChange, int nrTruckContractAfterChange, int amountAfterChange, String contractNameAfterChange) {
        contractsClose = new ContractsClose
                .ContractsCloseBuilder()
                .idSell(idSellAfterChange)
                .idCustomer(idCustomerAfterChange)
                .contractName(customerNameAfterChange)
                .materialName(materialNameAfterChange)
                .nrTruck(nrTruckAfterChange)
                .nrTruckContract(nrTruckContractAfterChange)
                .amount(amountAfterChange)
                .contractName(contractNameAfterChange)
                .build();
        defaultContractsCloseService.addUpdateContract(contractsClose);
    }

    @Override
    public void remove(Long id) {
        defaultContractsCloseService.deleteContract(id);
    }

    @Override
    public Object findById(Long id) {
        Optional<ContractsClose> foundContractClose = defaultContractsCloseService.findById(id);
        return foundContractClose.get();
    }

    @Override
    public List<ContractsClose> findAll() {
        return defaultContractsCloseService.findAll();
    }
    @Override
    public void updateRecord(Object object) {
        ContractsClose contractsClosed =(ContractsClose) object;
        defaultContractsCloseService.addUpdateContract(contractsClosed);
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
                contractsClose.setMaterialName(contractsOpenBuy.getMaterialName());
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
                contractsClose.setMaterialName(contractsOpenSell.getMaterialName());
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
