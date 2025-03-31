package com.kevin.todo.todo_application.usermanagement.repository;

import com.kevin.todo.todo_application.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String user1);

    boolean existsByEmail(String email);
}
