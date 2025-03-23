package com.kevin.todo.todo_application.todo.controller;

import com.kevin.todo.todo_application.todo.dto.TodoDto;
import com.kevin.todo.todo_application.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/all")
    public ResponseEntity <TodoDto>  retrieveAllTodos() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> createTodo(@Valid @RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(todoService.save(todoDto));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTodo(@Valid @RequestBody TodoDto todoDto, @PathVariable long id) {
        return ResponseEntity.ok(todoService.update(todoDto, id));
    }

    @DeleteMapping("/delete-todo/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable long id) {
        todoService.delete(id);
        return ResponseEntity.status(200).body("Todo deleted successfully");
    }

}
