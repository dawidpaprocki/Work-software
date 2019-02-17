package crud.services;

public interface DaoCustomerService<T> extends DaoService {

    void add();

    void update(int id, String afterChangeName, String afterChangeCountry);

}

