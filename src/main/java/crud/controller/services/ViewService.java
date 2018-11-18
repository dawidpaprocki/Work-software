package crud.controller.services;

import java.util.List;

public interface ViewService<T> {

    void updateRecord(String idOfColumn, String newValue, int idOfRow);
    List<T> selectList();

}
