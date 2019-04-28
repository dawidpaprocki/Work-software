package crud.services.interfaces;

public interface AllTablesUpdateRecordService {
    void updateRecord(Class clazz,String columnName, String newValue, Long idRow);
}
