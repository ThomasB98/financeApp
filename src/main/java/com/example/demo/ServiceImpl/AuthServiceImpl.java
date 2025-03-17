package com.example.demo.ServiceImpl;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Entity.Admin;
import com.example.demo.Entity.User;
import com.example.demo.Service.IAuthService;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public String registerUser(User user) {
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public User loginUser(LoginDto user) {
        User existingUser = userRepository.findByUsername(user.getUsernameOrEmail());
        if (existingUser != null && user.getPassword().equals(existingUser.getPassword())) {
            return existingUser;  // Return user details on successful login
        }
        return null;  // Return null for invalid credentials
    }

    @Override
    public Admin loginAdmin(LoginDto user) {
        Admin existingAdmin = adminRepository.findByUsername(user.getUsernameOrEmail());
        if (existingAdmin != null && user.getPassword().equals(existingAdmin.getPassword())) {
            return existingAdmin;  // Return admin details on successful login
        }
        return null;  // Return null for invalid credentials
    }
}
