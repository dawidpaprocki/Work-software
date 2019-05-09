package crud.services.interfaces;

import crud.model.User;

import java.util.List;

public interface UserService {
     User findById(Long id);
     void addUser(User user);
     void deleteUser(User userId);
     List<User> findAll();
}
