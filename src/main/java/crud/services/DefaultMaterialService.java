package crud.services;

import crud.model.Material;
import crud.repository.MaterialRepository;
import crud.services.interfaces.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.PropertiesReader;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultMaterialService implements MaterialService {

    private MaterialRepository materialRepository;

    private PropertiesReader propertiesFile;

    @Autowired
    public DefaultMaterialService(MaterialRepository materialRepository, PropertiesReader propertiesFile) {
        this.materialRepository = materialRepository;
        this.propertiesFile = propertiesFile;
    }

    @Override
    public void add(String nameOfMaterial) {
        Material newMaterial = Material.builder()
                .name(nameOfMaterial)
                .build();
        materialRepository.save(newMaterial);
    }

    @Override
    public void update(Material material, String afterChange) {
        material.setName(afterChange);
        materialRepository.save(material);
    }

    @Override
    public void remove(Material materialForDelete) {
        materialRepository.delete(materialForDelete);
    }

    @Override
    public Material findById(Long id) {
        Optional<Material> foundMaterial = materialRepository.findById(id);
        return foundMaterial.orElseGet(() -> Material.builder()
                .name(propertiesFile.getPropertiesFile().getProperty("lackOfMaterial"))
                .build());
    }

    @Override
    public Material findByName(String name) {
     return  materialRepository.findByName(name).orElse(
                 Material.builder()
                         .name(propertiesFile.getPropertiesFile().getProperty("lackOfMaterial"))
                         .build()
         );
    }

    @Override
    public List selectList()
    {
        return materialRepository.findAll();
    }
}
