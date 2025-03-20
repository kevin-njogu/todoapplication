package com.kevin.todo.todo_application.todo;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/basic-auth")
    public String basicAuthentication() {
        return "Success";
    }

    @GetMapping("/todos")
    public Page<Todo> retrieveAllTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return todoService.findAll(pageable);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@Valid @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.save(todo));
    }

    /*
    @GetMapping("/todos/{id}")
    public Todo retrieveOneTodo(@PathVariable long id) {
        return todoRepository.findById(id).orElse(null);
    }
     */

    @PutMapping("/update-todo/{id}")
    public ResponseEntity<?> updateTodo(@Valid @RequestBody Todo newTodo, @PathVariable long id) {
        return ResponseEntity.ok(todoService.update(newTodo, id));
    }

    @DeleteMapping("/delete-todo/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable long id) {
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
