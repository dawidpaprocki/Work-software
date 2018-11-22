package crud.controller.services;

public interface DaoMaterialService extends DaoService {

    void add(String what);

    void update(int id, String afterChange);
}
