package dev.spring.sbbpart2and3.service;

import dev.spring.sbbpart2and3.domain.SiteUser;
import dev.spring.sbbpart2and3.exception.NoDataFoundException;
import dev.spring.sbbpart2and3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SiteUser createUser(String username, String password, String email) {
        SiteUser user = new SiteUser(username, passwordEncoder.encode(password), email);
        userRepository.save(user);
        return user;
    }

    public SiteUser findUserByUsername(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(() ->
                new NoDataFoundException("존재하지 않는 사용자입니다."));
    }

}
