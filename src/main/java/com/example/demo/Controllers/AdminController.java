package com.example.demo.Controllers;

import com.example.demo.Entity.User;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @PutMapping("/verify/{id}")
    public String verifyUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setVerified(true);
        userRepository.save(user);
        return "User Verified!";
    }
}
