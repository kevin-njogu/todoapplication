package com.kevin.todo.todo_application.controller;


import com.kevin.todo.todo_application.dto.UserRequestResponse;
import com.kevin.todo.todo_application.entity.OurUser;
import com.kevin.todo.todo_application.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserRequestResponse>  register(@RequestBody UserRequestResponse registrationReq) {
        return ResponseEntity.ok(userManagementService.register(registrationReq));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserRequestResponse> login(@RequestBody UserRequestResponse loginReq) {
        return ResponseEntity.ok(userManagementService.login(loginReq));
    }

    @PostMapping("auth/refresh")
    public ResponseEntity<UserRequestResponse> refreshToken(@RequestBody UserRequestResponse refreshTokenReq) {
        return ResponseEntity.ok(userManagementService.refreshToken(refreshTokenReq));
    }

    @GetMapping("/admin/users")
    public ResponseEntity<UserRequestResponse> allUsers() {
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @GetMapping("/admin/user/{id}")
    public ResponseEntity<UserRequestResponse> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userManagementService.getUsersById(id));
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<UserRequestResponse> updateUser(@PathVariable Integer id,@RequestBody OurUser  updateUserReq){
        return ResponseEntity.ok(userManagementService.updateUser(id, updateUserReq));
    }

    @GetMapping("/adminuser/view/profile")
    public  ResponseEntity<UserRequestResponse> viewProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserRequestResponse response = userManagementService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).build();
    }

    @DeleteMapping("admin/delete/{id}")
    public ResponseEntity<UserRequestResponse> deleteUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userManagementService.deleteUser(id));
    }
}

