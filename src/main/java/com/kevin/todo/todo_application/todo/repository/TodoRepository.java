package com.kevin.todo.todo_application.todo.repository;

import com.kevin.todo.todo_application.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByOwnerUsername(String ownerUsername);
}
