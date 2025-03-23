package com.kevin.todo.todo_application.usermanagement.service;

import com.kevin.todo.todo_application.usermanagement.commons.Role;
import com.kevin.todo.todo_application.usermanagement.dto.AuthenticationRequest;
import com.kevin.todo.todo_application.usermanagement.dto.AuthenticationResponse;
import com.kevin.todo.todo_application.usermanagement.dto.RegisterRequest;
import com.kevin.todo.todo_application.usermanagement.model.User;
import com.kevin.todo.todo_application.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.valueOf(registerRequest.getRole()))
                .build();
        User  savedUser =  userRepository.save(user);
        var jwt = jwtService.generateJwtToken(user );
        return AuthenticationResponse.builder()
                .token(jwt)
                .name(savedUser.getName())
                .role(savedUser.getRole().toString())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
                    );
            var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            var jwt = jwtService.generateJwtToken(user);
            return AuthenticationResponse.builder()
                    .name(user.getName())
                    .role(String.valueOf(user.getRole()))
                    .token(jwt)
                    .message("Logged in successfully")
                    .statusCode(200)
                    .build();
        }catch (AuthenticationException e) {
            return AuthenticationResponse.builder()
                    .message(e.getMessage())
                    .statusCode(400)
                    .build();
        }
    }
}
