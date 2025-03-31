package com.kevin.todo.todo_application.todo.service;

import com.kevin.todo.todo_application.todo.repository.TodoRepository;
import com.kevin.todo.todo_application.todo.dto.TodoDto;
import com.kevin.todo.todo_application.todo.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoDto findAll(String ownerUsername) {
        try{
        List<Todo> todos = todoRepository.findByOwnerUsername(ownerUsername);
        if (todos.isEmpty()) {
            throw new RuntimeException("No todos found");
        }
        return TodoDto.builder()
                .todos(todos)
                .build();
        } catch (Exception e) {
            return TodoDto.builder().message(e.getMessage()).build();
        }
    }
    public TodoDto save(TodoDto todoDto, String ownerUsername) {
        try {
            if (todoDto == null) {
                throw new IllegalArgumentException("Todo cannot be null");
            }
            Todo todo = Todo.builder()
                    .description(todoDto.getDescription())
                    .targetDate(todoDto.getTargetDate())
                    .complete(todoDto.isComplete())
                    .ownerUsername(ownerUsername)
                    .build();
            todoRepository.save(todo);
            return TodoDto.builder()
                    .message("Todo saved successfully")
                    .build();
        } catch (Exception e) {
            return TodoDto.builder().message(e.getMessage()).build();
        }
    }
    public TodoDto update(TodoDto todoDto, long id, String ownerUsername) {
        try{
            if (todoDto == null) {
                throw new IllegalArgumentException("Todo cannot be null");
            }
            if (id <= 0) {
                throw new IllegalArgumentException("Todo ID must be greater than 0");
            }
            todoRepository.findById(id)
                    .map(todo -> {
                        todo.setDescription(todoDto.getDescription());
                        todo.setTargetDate(todoDto.getTargetDate());
                        todo.setComplete(todoDto.isComplete());
                        return todoRepository.save(todo);
                    }).orElseGet(() -> todoRepository.save(
                            Todo.builder()
                                    .description(todoDto.getDescription())
                                    .targetDate(todoDto.getTargetDate())
                                    .complete(todoDto.isComplete())
                                    .build()
                    ));
            return TodoDto.builder()
                    .message("Todo updated successfully")
                    .build();
        } catch (Exception e) {
            return TodoDto.builder().message(e.getMessage()).build();
        }
    }
    public TodoDto delete(long id, String ownerUsername) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("Todo ID must be greater than 0");
            }
            if (todoRepository.findById(id).isPresent()) {
                todoRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("Todo with ID " + id + " not found");
            }
            return TodoDto.builder()
                    .message("Todo deleted successfully")
                    .build();
        } catch (Exception e) {
            return TodoDto.builder().message(e.getMessage()).build();
        }
    }
}
