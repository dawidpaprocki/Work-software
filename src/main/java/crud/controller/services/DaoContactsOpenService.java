package crud.controller.services;

public interface DaoContactsOpenService<T>   {

    void add(int idSell, int idCustomer, String customerName, String idName,
             int nrTruck, int nrTruckContract, int amount,
             String contractName);

    void update(int id, int idSellAfterChange, int idCustomerAfterChange, String customerNameAfterChange, String idNameAfterChange,
                int nrTruckAfterChange, int nrTruckContractAfterChange, int amountAfterChange,
                String contractNameAfterChange);
    void remove(int id);
    T findById(int id);

}
