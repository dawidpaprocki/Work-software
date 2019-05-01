package crud.services;

import crud.model.AccessLevel;
import crud.model.Role;
import crud.model.User;
import crud.repository.AccessLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class MyUserDetails implements UserDetails {
    private User user;

    private AccessLevelRepository accessLevelRepository;

    @Autowired
    public MyUserDetails(User user, AccessLevelRepository accessLevelRepository) {
        this.user = user;
        this.accessLevelRepository = accessLevelRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<AccessLevel> foundAccessLevelForUser = accessLevelRepository.findAllByUser(user);
        List<Role> foundRolesForUser = foundAccessLevelForUser.stream()
                .map(AccessLevel::getRole)
                .collect(Collectors.toList());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        foundRolesForUser.forEach((foundRole ->
                grantedAuthorities.add(new SimpleGrantedAuthority(foundRole.getName()))));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
       return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
