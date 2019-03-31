package crud.services;

import org.springframework.stereotype.Service;

@Service
public interface DaoMaterialService extends DaoService {

    void add(String what);

    void update(int id, String afterChange);
}
