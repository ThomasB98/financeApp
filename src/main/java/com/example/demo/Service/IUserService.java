package com.example.demo.Service;

import com.example.demo.Entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User registerUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
