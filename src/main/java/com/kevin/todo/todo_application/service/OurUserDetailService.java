package com.kevin.todo.todo_application.service;

import com.kevin.todo.todo_application.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OurUserDetailService implements UserDetailsService {

    private UsersRepository usersRepository;

    public OurUserDetailService(UsersRepository usersRepository) {
        this.usersRepository=usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository
                .findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with email" + username));
    }
}
