package com.kevin.todo.todo_application.todo.controller;

import com.kevin.todo.todo_application.todo.dto.TodoDto;
import com.kevin.todo.todo_application.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    @GetMapping("/getalltodos")
    public ResponseEntity <TodoDto>  retrieveAllTodos(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        System.out.println(username);
        return ResponseEntity.ok(todoService.findAll(username));
    }
    @PostMapping("/addtodo")
    public ResponseEntity<?> createTodo(@Valid @RequestBody TodoDto todoDto, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        System.out.println(username);
        return ResponseEntity.ok(todoService.save(todoDto, username));
    }
    @PutMapping("/updatetodo/{id}")
    public ResponseEntity<?> updateTodo(@Valid @RequestBody TodoDto todoDto, @PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return ResponseEntity.ok(todoService.update(todoDto, id, username));
    }
    @DeleteMapping("/deletetodo/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return ResponseEntity.ok( todoService.delete(id, username));
    }

}
