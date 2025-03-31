package com.kevin.todo.todo_application.todo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String description;
    private LocalDate targetDate;
    private boolean complete;
    private String ownerUsername;
}
