package crud.controller.controllers;

import crud.controller.services.DaoMaterialService;
import crud.model.GenericDao;
import entity.Material;

import java.util.List;
import java.util.stream.Collectors;

public class DaoMaterialController implements DaoMaterialService {

    private final GenericDao dao;

    public DaoMaterialController(GenericDao dao) {
        this.dao = dao;
    }

    Material material;
    @Override
    public void add(String what) {
        material = new Material();
        material.setName(what);
        dao.insert(material);
    }

    @Override
    public void update(int id, String afterChange) {
        material = (Material) dao.findById(id);
        material.setName(afterChange);
        dao.update(material);

    }



    @Override
    public void remove(int id) {
        material = (Material)  dao.findById(id);
        dao.delete(material);
    }

    @Override
    public Material findById(int id) {
        material = (Material) dao.findById(id);
        return material;
    }

    @Override
    public List<Material> findByName(String name) {
        List<Material> list= dao.select();
        list.stream().filter(e->e.getName().equals(name)).collect(Collectors.toList());
        return  list.stream().filter(e->e.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<String> selectList() {
        material = new Material();
        List<Material> select = dao.select();
        return select.stream().map(Material::getName).collect(Collectors.toList());
    }
}
