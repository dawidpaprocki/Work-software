package crud.services;

import crud.model.User;
import crud.repository.AccessLevelRepository;
import crud.repository.UserRepository;
import crud.services.interfaces.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserService implements UserDetailsService, UserService {

    private UserRepository userRepository;
    private AccessLevelRepository accessLevelRepository;

    public DefaultUserService(UserRepository userRepository, AccessLevelRepository accessLevelRepository) {
        this.userRepository = userRepository;
        this.accessLevelRepository = accessLevelRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byName = userRepository.findByLogin(username);
        return new MyUserDetails(byName.get(),accessLevelRepository);
    }
}
