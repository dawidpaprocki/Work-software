import crud.controller.controllers.DaoMaterialController;
import crud.model.GenericDaoImpl;
import entity.Material;
import org.junit.*;
import utils.HibernateUtils;


import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class DaoMaterialControllerTest {
    EntityManager entityManager;
    DaoMaterialController daoMaterialController;
    GenericDaoImpl dao;
    Material material;

    @Before
    public void before() {
        entityManager = HibernateUtils.getSessionFactory().createEntityManager();

        material = new Material();
        material.setName("Test");
        entityManager.getTransaction().begin();
        entityManager.persist(material);
        entityManager.getTransaction().commit();


        dao = new GenericDaoImpl(entityManager, Material.class);
        daoMaterialController =  DaoMaterialController.builder().dao(dao).build();


    }

    @After
    public void after() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("Delete  From Material ").executeUpdate();
        entityManager.getTransaction().commit();


    }

    @Test
    public void addedMaterialTest() {

        //when
        daoMaterialController.add("Material");
        //then
        List<Material> select = dao.select();
        List<String> collect = select.stream().map(Material::getName).collect(Collectors.toList());

        assertTrue(collect.contains("Material"));
    }

    @Test
    public void updateMaterialTest() {
        //given
        Material materialObject = (Material) dao.select().get(0);
        int id = materialObject.getId();
        //when
        daoMaterialController.update(id, "change");
        Material expected = (Material) dao.findById(id);
        //then
        assertEquals(expected.getName(), "change");

    }

    @Test
    public void findTestTrue() {
        //given
        Material materialObject = (Material) dao.select().get(0);
        int givenId =  materialObject.getId();
        //when
        material = daoMaterialController.findById(givenId);
        //then
        assertEquals(material.getId(), givenId);
    }

    @Test
    public void findTestFalse() {
        //given
        Material materialObject = (Material) dao.select().get(0);
        int givenId =  materialObject.getId();
        //when
        material = daoMaterialController.findById(givenId);
        //then
        assertNotEquals(material.getId(), givenId + 1);
    }

    @Test
    public void selectListOfMaterialsTestTrue() {

        daoMaterialController.selectList();
        assertTrue(daoMaterialController.selectList().size() > 0);
    }

    @Test
    public void selectListOfMaterialsTestFalse() {
        daoMaterialController.selectList();
        assertFalse(daoMaterialController.selectList().size() > 10);
    }

    @Test
    public void removeMaterialTest() {
        //given
        Material materialObject = (Material) dao.select().get(0);
        //when
        daoMaterialController.remove(materialObject.getId());
        //then
        assertEquals(dao.select().size(), 0);
    }

    @Test
    public void findByNameTest() {
        //given
        Material materialObject = (Material) dao.select().get(0);
        int id = materialObject.getId();
        //when
        List<Material> test = daoMaterialController.findByName("Test");
        //then
        assertEquals(test.get(0).getId(), id);
    }


}