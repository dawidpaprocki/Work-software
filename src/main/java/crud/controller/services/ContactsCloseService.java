package crud.controller.services;

import crud.model.GenericDao;

public interface ContactsCloseService<T> {
    void CheckStatusTransfer(int id, T tableFormGet, GenericDao daoFrom);
}
