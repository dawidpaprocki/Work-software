package crud.services;

public interface DaoMaterialService extends DaoService {
    void add(String nameOfMaterial);
    void update(Long id, String afterChange);
}
