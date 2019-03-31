package crud.services;

import org.springframework.stereotype.Service;

@Service
public interface DaoContactsOpenService<T>   {

    void add();

    void update(int idSellAfterChange, int idCustomerAfterChange, String customerNameAfterChange, String materialNameAfterChange,
                int nrTruckAfterChange, int nrTruckContractAfterChange, int amountAfterChange,
                String contractNameAfterChange);
    void remove(Long id);
    T findById(Long id);
}
