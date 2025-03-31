package com.kevin.todo.todo_application.reconciler.manualreconcile.controller;

import com.kevin.todo.todo_application.reconciler.manualreconcile.dto.ManualReconcileResponseDto;
import com.kevin.todo.todo_application.reconciler.manualreconcile.service.ManualReconcileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/manualreconcile")
public class ManualReconcilitionController {
    private final ManualReconcileService manualReconcileService;
    @GetMapping("/unmatched")
    public ResponseEntity<ManualReconcileResponseDto> getUnmatched(
            @RequestParam(defaultValue = "equity") String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(manualReconcileService.getUnMatchedItems(type, page, size, sortBy, direction));
    }
}
