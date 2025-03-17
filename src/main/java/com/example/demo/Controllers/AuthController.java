package com.example.demo.Controllers;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Entity.Admin;
import com.example.demo.Entity.User;
import com.example.demo.Service.IAuthService;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        String response = authService.registerUser(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto user) {
        User loggedInUser = authService.loginUser(user);
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginDto user) {
        Admin loggedInAdmin = authService.loginAdmin(user);
        if (loggedInAdmin != null) {
            return ResponseEntity.ok(loggedInAdmin);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

}
