package com.kevin.todo.todo_application.todo.repository;

import com.kevin.todo.todo_application.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByOwnerUsername(String ownerUsername);
}
