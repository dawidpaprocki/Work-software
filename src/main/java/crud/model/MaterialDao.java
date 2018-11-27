//package crud.model;
//
//import entity.Material;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import java.util.List;
//
//
//public class MaterialDao extends GenericDaoImpl<Material> {
//    private final EntityManager entityManager;
//
//    public MaterialDao(EntityManager entityManager, Class<Material> clazz) {
//        super(entityManager, clazz);
//        this.entityManager = entityManager;
//    }
//
//    public List<Material> find(){
//        String hql = "SELECT "+ name +"FROM "+ clazz.getSimpleName()+" Where "+ Where+ " = " + what;
//        Query query = entityManager.createQuery(hql);
//        return entityManager.createQuery("select e From Material e ", Material.class)
//                .getResultList();
//    }
//}
