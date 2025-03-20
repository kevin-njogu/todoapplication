package com.kevin.todo.todo_application.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Page<Todo> findAll(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo update(Todo newTodo, long id) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setDescription(newTodo.getDescription());
                    todo.setTargetDate(newTodo.getTargetDate());
                    todo.setComplete(newTodo.isComplete());
                    return todoRepository.save(todo);
                }).orElseGet(() -> todoRepository.save(newTodo));

    }

    public void deleteById(long id) {
        todoRepository.deleteById(id);
    }

}
