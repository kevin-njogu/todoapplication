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


    /**
     * Finds all Todos in the database.
     *
     * @return a TodoDto with a List of all Todos in the database
     */
    public TodoDto findAll() {
        // Retrieve the list of all Todos from the database
        List<Todo> todos = todoRepository.findAll();

        // Return a TodoDto with the list of Todos
        return TodoDto.builder()
                .todos(todos)
                .build();
    }

    /**
     * Save a new Todo.
     *
     * @param todoDto the Todo fields to be saved
     * @return a TodoDto with a message indicating the success of the save
     */
    public TodoDto save(TodoDto todoDto) {
        // Create a new Todo from the provided TodoDto
        Todo todo = Todo.builder()
                .description(todoDto.getDescription())
                .targetDate(todoDto.getTargetDate())
                .complete(todoDto.isComplete())
                .build();

        // Save the Todo to the database
        todoRepository.save(todo);

        // Return a TodoDto with a message indicating the success of the save
        return TodoDto.builder()
                .message("Todo saved successfully")
                .build();
    }

    /**
     * Update a Todo by its ID.
     *
     * @param todoDto the updated Todo fields
     * @param id the ID of the Todo to be updated
     * @return a TodoDto with a message indicating the success of the update
     */
    public TodoDto update(TodoDto todoDto, long id) {
        // Find the Todo by id
        todoRepository.findById(id)
                .map(todo -> {
                    // Update the Todo fields
                    todo.setDescription(todoDto.getDescription());
                    todo.setTargetDate(todoDto.getTargetDate());
                    todo.setComplete(todoDto.isComplete());
                    // Save the updated Todo
                    return todoRepository.save(todo);
                }).orElseGet(() -> {
                    // If the Todo does not exist, create a new one
                    return todoRepository.save(
                            Todo.builder()
                                    .description(todoDto.getDescription())
                                    .targetDate(todoDto.getTargetDate())
                                    .complete(todoDto.isComplete())
                                    .build()
                    );
                });
        // Return a TodoDto with a success message
        return todoDto.builder()
                .message("Todo updated successfully")
                .build();
    }

    /**
     * Delete a Todo by its ID.
     *
     * @param id the ID of the Todo to be deleted
     */
    public void delete(long id) {
        // Check if the todo exists
        if (todoRepository.findById(id).isPresent()) {
            // Delete the todo
            todoRepository.deleteById(id);
        } else {
            // Todo not found
            throw new IllegalArgumentException("Todo with ID " + id + " not found");
        }
    }
}
