package crud.controller.services;

public interface DaoCustomerService<T> extends DaoService {

    void add(String name, String country);

    void update(int id, String afterChangeName, String afterChangeCountry);

}

