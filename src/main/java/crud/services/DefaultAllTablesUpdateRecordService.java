package crud.services;

import crud.services.interfaces.AllTablesUpdateRecordService;
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
        String hql = "UPDATE " + clazz.getSimpleName() + " a SET a." + columnName.substring(0,columnName.length()-6) + "  = :newValue where id = :idRow ";
        Query managerQuery = entityManager.createQuery(hql);
        try {
            managerQuery.setParameter("newValue", Integer.parseInt(newValue));
        } catch (Exception e) {
            managerQuery.setParameter("newValue", newValue);
        }
        managerQuery.setParameter("idRow", idRow);
        managerQuery.executeUpdate();
    }
}
