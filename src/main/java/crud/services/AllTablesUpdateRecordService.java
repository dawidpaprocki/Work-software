package crud.services;

public interface AllTablesUpdateRecordService {
    void updateRecord(Class clazz,String columnName, String newValue, Long idRow);
}
