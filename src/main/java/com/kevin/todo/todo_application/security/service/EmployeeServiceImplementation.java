package com.kevin.todo.todo_application.security.service;

import com.kevin.todo.todo_application.security.dto.RegistrationDTO;
import com.kevin.todo.todo_application.security.model.Employee;
import com.kevin.todo.todo_application.security.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImplementation  implements UserDetailsService {

    private  EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    public  EmployeeServiceImplementation(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository=employeeRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public ResponseEntity<String> createNewUser(RegistrationDTO registrationDTO) {
        Employee employee = convertDTOtoEntity(registrationDTO);
        try{
            boolean userExists = employeeRepository.existsByEmail(employee.getEmail());
            if(userExists){
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("The email is already exists");
            }else {
                String hashPassword = passwordEncoder.encode(employee.getPassword());
                employee.setPassword(hashPassword);
                Employee newEmployee = employeeRepository.save(employee);
                if (newEmployee.getId() > 0) {
                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body("User has been created successfully");
                } else {
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body("User creation failed");
                }
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occured on user creations " + e.getMessage());
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Employee employee = employeeRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User details not found for user " + username)
        );
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(employee.getRole()));
        return  new User(employee.getEmail(), employee.getPassword(), authorities);
    }


    private Employee convertDTOtoEntity(RegistrationDTO registrationDTO) {
        Employee employee = new Employee(
                registrationDTO.getEmail(),
                registrationDTO.getPassword(),
                registrationDTO.getFirstName(),
                registrationDTO.getLastName(),
                registrationDTO.getRole());
        return employee;
    }

}
