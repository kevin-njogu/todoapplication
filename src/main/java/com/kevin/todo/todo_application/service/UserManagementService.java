package com.kevin.todo.todo_application.service;

import com.kevin.todo.todo_application.dto.UserRequestResponse;
import com.kevin.todo.todo_application.entity.OurUser;
import com.kevin.todo.todo_application.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRequestResponse register(UserRequestResponse registrationRequest) {
        UserRequestResponse response = new UserRequestResponse();

        try{
            OurUser ourUser = new OurUser();
            ourUser.setEmail(registrationRequest.getEmail());
            ourUser.setName(registrationRequest.getName());
            ourUser.setRole(registrationRequest.getRole());
            ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            OurUser newUser = usersRepository.save(ourUser);
            if(newUser.getId() > 0) {
                response.setOurUser(newUser);
                response.setMessage("User Saved Successfully");
                response.setStatusCode(201);
            }
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public  UserRequestResponse login(UserRequestResponse loginRequest) {
        UserRequestResponse response = new UserRequestResponse();

        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                    );
            var user = usersRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfullly logged in");
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setError("Invalid credentials");
        }
        return response;
    }


    public UserRequestResponse refreshToken(UserRequestResponse refreshTokenRequest){
        UserRequestResponse response = new UserRequestResponse();
        try{
            String userEmail = jwtUtils.extractUserName(refreshTokenRequest.getToken());
            OurUser user = usersRepository.findByEmail(userEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)) {
                var jwt = jwtUtils.generateToken(user);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }


    public UserRequestResponse getAllUsers() {
        UserRequestResponse response = new UserRequestResponse();

        try {
            List<OurUser> users = usersRepository.findAll();
            if (!users.isEmpty()) {
                response.setOurUsers(users);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                response.setStatusCode(404);
                response.setMessage("No users found");
            }
            return response;
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred: " + e.getMessage());
            return response;
        }
    }


    public UserRequestResponse  getUsersById(Integer id) {
        UserRequestResponse response = new UserRequestResponse();
        try {
            OurUser user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
            response.setOurUser(user);
            response.setStatusCode(200);
            response.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred: " + e.getMessage());
        }
        return response;
    }


    public UserRequestResponse deleteUser(Integer userId) {
        UserRequestResponse response = new UserRequestResponse();
        try {
            Optional<OurUser> userOptional = usersRepository.findById(userId);
            if (userOptional.isPresent()) {
                usersRepository.deleteById(userId);
                response.setStatusCode(200);
                response.setMessage("User deleted successfully");
            } else {
                response.setStatusCode(404);
                response.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return response;
    }

    public UserRequestResponse updateUser(Integer userId, OurUser updatedUser) {
        UserRequestResponse reqRes = new UserRequestResponse();
        try {
            Optional<OurUser> user = usersRepository.findById(userId);
            if (user.isPresent()) {
                OurUser existingUser = user.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setName(updatedUser.getName());
                existingUser.setRole(updatedUser.getRole());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                OurUser savedUser = usersRepository.save(existingUser);
                reqRes.setOurUser(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }


    public UserRequestResponse getMyInfo(String email){
        UserRequestResponse response = new UserRequestResponse();
        try {
            Optional<OurUser> user = usersRepository.findByEmail(email);
            if (user.isPresent()) {
                response.setOurUser(user.get());
                response.setStatusCode(200);
                response.setMessage("successful");
            } else {
                response.setStatusCode(404);
                response.setMessage("User not found for update");
            }

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return response;
    }
}


