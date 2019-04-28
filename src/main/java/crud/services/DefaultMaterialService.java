package crud.services;

import crud.repository.MaterialRepository;
import crud.model.Material;
import crud.services.interfaces.MaterialService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DefaultMaterialService implements MaterialService {

    private MaterialRepository materialRepository;

    public DefaultMaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public void add(String nameOfMaterial) {
        Material newMaterial = Material.builder().name(nameOfMaterial).build();
        materialRepository.save(newMaterial);
    }

    @Override
    public void update(Long id, String afterChange) {
        Material foundMaterial = materialRepository.findById(id).get();
        foundMaterial.setName(afterChange);
        materialRepository.save(foundMaterial);
    }

    @Override
    public void remove(Long id) {
        materialRepository.delete(materialRepository.findById(id).get());
    }

    @Override
    public Material findById(Long id) {
        Optional<Material> foundMaterial = materialRepository.findById(id);
        if (materialRepository.findById(id).isPresent()) {
            return foundMaterial.get();
        } else {
            return Material.builder().build();
        }
    }

    @Override
    public List findByName(String name) {
        if (!materialRepository.findByName(name).isEmpty()) {
            return materialRepository.findByName(name);
        }else {
            return new ArrayList();
        }
    }

    @Override
    public List selectList()
    {
        return materialRepository.findAll();
    }
}
