package crud.services;

public interface DaoContactsOpenService<T>   {

    void add();

    void update(int id, int idSellAfterChange, int idCustomerAfterChange, String customerNameAfterChange, String MaterialNameAfterChange,
                int nrTruckAfterChange, int nrTruckContractAfterChange, int amountAfterChange,
                String contractNameAfterChange);
    void remove(int id);
    T findById(int id);
    T find( String where , String name);
}
