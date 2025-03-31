package com.kevin.todo.todo_application.usermanagement.service;

import com.kevin.todo.todo_application.usermanagement.dto.UserDto;
import com.kevin.todo.todo_application.usermanagement.model.User;

import java.util.List;

public interface UserService {
    void updateUserRole(Long userId, String roleName);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    User findByUsername(String username);
}
