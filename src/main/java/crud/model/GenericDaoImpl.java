package crud.model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GenericDaoImpl<T> implements GenericDao<T> {

    private EntityManager entityManager;
    private Class<T> clazz;

    public GenericDaoImpl(EntityManager entityManager, Class<T> clazz) {
        this.entityManager = entityManager;
        this.clazz = clazz;
    }

    @Override
    public void insert(T obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(T obj) {
        entityManager.getTransaction().begin();
        entityManager.merge(obj);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(T obj) {
        entityManager.getTransaction().begin();
        entityManager.remove(obj);
        entityManager.getTransaction().commit();
    }

    @Override
    public T findById(int id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public List<T> select() {
        return entityManager.createQuery("FROM " + clazz.getSimpleName(), clazz)
                .getResultList();
    }



    @Override

    public void  query(String query, String newValue,int idRow) {
        entityManager.getTransaction().begin();
        entityManager.clear();
        String hql = "UPDATE "+clazz.getSimpleName()+" SET " + query + "  =:newValue where id = :idRow ";
        Query query1 = entityManager.createQuery(hql);
        query1.setParameter("newValue",newValue);
        query1.setParameter("idRow",idRow);
        query1.executeUpdate();
        entityManager.getTransaction().commit();

    }


}
