package com.kevin.todo.todo_application.security.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class LoginResponseDTO {

    private String email;
    private List<GrantedAuthority> authorities;

    public LoginResponseDTO() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
