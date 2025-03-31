package com.kevin.todo.todo_application.usermanagement.repository;

import com.kevin.todo.todo_application.usermanagement.model.Role;
import com.kevin.todo.todo_application.usermanagement.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Roles, Long> {
    Optional<Roles> findByRoleName(Role role);
}
