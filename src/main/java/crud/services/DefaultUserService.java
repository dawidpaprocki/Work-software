package crud.services;

import crud.model.User;
import crud.repository.AccessLevelRepository;
import crud.repository.UserRepository;
import crud.services.interfaces.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tools.PropertiesReader;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserService implements UserDetailsService, UserService {

    private UserRepository userRepository;
    private AccessLevelRepository accessLevelRepository;

    private PropertiesReader propertiesReader;


    public DefaultUserService(UserRepository userRepository, AccessLevelRepository accessLevelRepository) {
        this.userRepository = userRepository;
        this.accessLevelRepository = accessLevelRepository;
    }

    @Override
    public User findById(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElseGet(() -> User.builder().name(propertiesReader.getPropertiesFile().getProperty("lackOfMaterial")).build());
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
        Optional<User> foundUser = userRepository.findByLogin(username);
        return new MyUserDetails(foundUser.orElseGet(() -> User.builder()
                .name(propertiesReader.getPropertiesFile().getProperty("lackOfMaterial"))
                .build())
                ,accessLevelRepository);
    }
}
