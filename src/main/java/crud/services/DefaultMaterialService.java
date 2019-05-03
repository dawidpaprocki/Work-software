package crud.services;

import crud.model.Material;
import crud.repository.MaterialRepository;
import crud.services.interfaces.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@PropertySource(value = "classpath:config.properties", encoding="UTF-8")
public class DefaultMaterialService implements MaterialService {

    private MaterialRepository materialRepository;

    private Environment propertiesFile;

    @Autowired
    public DefaultMaterialService(MaterialRepository materialRepository, Environment propertiesFile) {
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
    public void update(Long id, String afterChange) {
        Material foundMaterial = materialRepository.findById(id).get();
        foundMaterial.setName(afterChange);
        materialRepository.save(foundMaterial);
    }

    @Override
    public void remove(Material materialForDelete) {
        materialRepository.delete(materialForDelete);
    }

    @Override
    public Material findById(Long id) {
        Optional<Material> foundMaterial = materialRepository.findById(id);
        if (foundMaterial.isPresent()) {
            return foundMaterial.get();
        } else {
            return Material.builder().name(propertiesFile.getProperty("lackOfMaterial")).build();
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
