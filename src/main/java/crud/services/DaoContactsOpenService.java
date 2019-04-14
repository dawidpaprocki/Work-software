package crud.services;

import entity.ContractsOpenSell;

public interface DaoContactsOpenService<T>   {

    void add(ContractsOpenSell contractsOpenSell);

    void update(Long idSellAfterChange, Long idCustomerAfterChange, String customerNameAfterChange, String materialNameAfterChange,
                int nrTruckAfterChange, int nrTruckContractAfterChange, int amountAfterChange,
                String contractNameAfterChange);
    void remove(Long id);
    T findById(Long id);
}
