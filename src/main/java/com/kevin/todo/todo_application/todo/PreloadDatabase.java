package com.kevin.todo.todo_application.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class PreloadDatabase {

    /*
    @Bean
    public CommandLineRunner run(TodoRepository todoRepository) {
        return args -> {
            todoRepository.save(new Todo("Learn AWS", LocalDate.now().plusYears(2),false));
            todoRepository.save(new Todo("Learn Docker", LocalDate.now().plusYears(3),false));
            todoRepository.save(new Todo("Learn React", LocalDate.now().plusYears(1),false));
        };
    }
     */

}
