package com.kevin.todo.todo_application.security.service;

import com.kevin.todo.todo_application.security.model.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public Employee findEmployeeByEmail(String email);
}
