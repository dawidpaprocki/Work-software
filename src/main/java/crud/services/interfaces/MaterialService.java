package crud.services.interfaces;

import crud.model.Material;

import java.util.List;

public interface MaterialService {
    void remove(Material materialForDelete);
    Material findById(Long id);
    Material findByName(String name);
    List<Material> selectList();
    void add(String nameOfMaterial);
    void update(Material material, String afterChange);
}
