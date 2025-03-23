package com.kevin.todo.todo_application.usermanagement.service;


import com.kevin.todo.todo_application.usermanagement.dto.GetUserResponse;
import com.kevin.todo.todo_application.usermanagement.model.User;
import com.kevin.todo.todo_application.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class  AdminActionsService{

    private final UserRepository userRepository;

    public GetUserResponse findAllUsers() {
        List<User> users = userRepository.findAll();
        return GetUserResponse.builder().users(users).build();
    }
}
