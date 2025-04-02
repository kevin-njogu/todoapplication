package com.kevin.todo.todo_application.usermanagement.service;

import com.kevin.todo.todo_application.usermanagement.dto.UserDto;
import com.kevin.todo.todo_application.usermanagement.model.Role;
import com.kevin.todo.todo_application.usermanagement.model.Roles;
import com.kevin.todo.todo_application.usermanagement.model.User;
import com.kevin.todo.todo_application.usermanagement.repository.RoleRepository;
import com.kevin.todo.todo_application.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = Role.valueOf(roleName);
        Roles roles = roleRepository.findByRoleName(role).orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(roles);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List <UserDto> users = userRepository.findAll().stream().map(user -> convertToDto(user)).toList();
        return users;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    private UserDto convertToDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.isAccountNonLocked(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                user.getCredentialsExpiryDate(),
                user.getAccountExpiryDate(),
                user.getTwoFactorSecret(),
                user.isTwoFactorEnabled(),
                user.getSignUpMethod(),
                user.getRole(),
                user.getCreatedDate(),
                user.getUpdatedDate()
        );
    }
}
