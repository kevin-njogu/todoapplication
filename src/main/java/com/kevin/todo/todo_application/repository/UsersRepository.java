package com.kevin.todo.todo_application.repository;

import com.kevin.todo.todo_application.entity.OurUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<OurUser, Integer> {

    Optional<OurUser> findByEmail(String email);
}
