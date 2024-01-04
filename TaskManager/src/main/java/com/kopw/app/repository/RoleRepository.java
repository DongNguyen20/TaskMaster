package com.kopw.app.repository;

import com.kopw.app.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByUsers(Long userId);

    Role findByRoleName(String role);
}
