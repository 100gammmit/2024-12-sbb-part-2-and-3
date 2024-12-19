package dev.spring.sbbpart2and3.repository;

import dev.spring.sbbpart2and3.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
