package crud.services.interfaces;

import crud.model.Material;

import java.util.List;

public interface MaterialService {
    void remove(Long id);
    Material findById(Long id);
    List<Material> findByName(String name);
    List<Material> selectList();
    void add(String nameOfMaterial);
    void update(Long id, String afterChange);
}
