package dev.spring.sbbpart2and3.service;

import dev.spring.sbbpart2and3.domain.SiteUser;
import dev.spring.sbbpart2and3.exception.UserNotFoundException;
import dev.spring.sbbpart2and3.repository.UserRepository;
import dev.spring.sbbpart2and3.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SiteUser user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        List<GrantedAuthority> authorities = new ArrayList<>();
        if("admin".equals(username)) {
            authorities.add((new SimpleGrantedAuthority(UserRole.ADMIN.getValue())));
        } else {
            authorities.add((new SimpleGrantedAuthority(UserRole.USER.getValue())));
        }
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
