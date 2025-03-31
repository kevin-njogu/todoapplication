package com.kevin.todo.todo_application.reconciler.manualreconcile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManualReconcileRequestDto {
    private int id;
    private boolean isReconciled;
}
