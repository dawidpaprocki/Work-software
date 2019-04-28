package crud.services.interfaces;

import crud.model.User;

import java.util.List;

public interface UserService {
     void addUser(User user);
     void deleteUser(User userId);
     List<User> findAll();
}
