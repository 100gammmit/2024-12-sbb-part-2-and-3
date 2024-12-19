package dev.spring.sbbpart2and3.repository;

import dev.spring.sbbpart2and3.domain.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void save() {
        roleRepository.save(new Role("ROLE_ADMIN"));
    }
}
