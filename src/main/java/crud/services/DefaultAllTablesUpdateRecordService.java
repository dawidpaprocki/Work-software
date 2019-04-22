package crud.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
@Service
public class DefaultAllTablesUpdateRecordService implements AllTablesUpdateRecordService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void updateRecord(Class clazz, String columnName, String newValue, Long idRow) {
        String hql = "UPDATE " + clazz.getSimpleName() + " a SET a." + columnName + "  = :newValue where id = :idRow ";
        Query query1 = entityManager.createQuery(hql);
        try {
            query1.setParameter("newValue", Integer.parseInt(newValue));
        } catch (Exception e) {
            query1.setParameter("newValue", newValue);
        }
        query1.setParameter("idRow", idRow);
        query1.executeUpdate();
    }
}
