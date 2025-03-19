package com.example.demo.Controllers;

import com.example.demo.Entity.User;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Service.IAdminService;
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
    private IAdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    @PutMapping("/verify/user/{id}")
    public String verifyUser(@PathVariable Long id) {
        if(id<=0){
            throw new ResourceNotFoundException("Resource with ID " + id + " not found.");
        }
        return adminService.verifyUser(id);
    }

    @PutMapping("/verify/card/{id}")
    public String verifyCard(@PathVariable Long id){
        if(id<=0){
            throw new ResourceNotFoundException("Resource with ID " + id + " not found.");
        }
        return adminService.verifyCard(id);
    }
}
