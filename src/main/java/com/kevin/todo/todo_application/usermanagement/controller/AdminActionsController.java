package com.kevin.todo.todo_application.usermanagement.controller;

import com.kevin.todo.todo_application.usermanagement.dto.GetUserResponse;
import com.kevin.todo.todo_application.usermanagement.service.AdminActionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminActionsController {

    private final AdminActionsService adminActionsService;

    @GetMapping("/users")
    public ResponseEntity<GetUserResponse> getUsers() {
        return ResponseEntity.ok(adminActionsService.findAllUsers());
    }
}
