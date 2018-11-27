package crud.controller.services;

import crud.model.GenericDao;

public interface ContactsCloseService<T> {
    void CheckStatusTransfer(int id, Class tableFormGet, GenericDao daoFrom);
}
