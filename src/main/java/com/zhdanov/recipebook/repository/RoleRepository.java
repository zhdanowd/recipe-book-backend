package com.zhdanov.recipebook.repository;

import com.zhdanov.recipebook.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByRoleName(String roleName);
}
