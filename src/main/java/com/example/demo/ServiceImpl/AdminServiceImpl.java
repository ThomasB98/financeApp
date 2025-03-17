package com.example.demo.ServiceImpl;

import com.example.demo.Entity.User;
import com.example.demo.Service.IAdminService;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String verifyUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setVerified(true);
        userRepository.save(user);
        return "User Verified!";
    }
}
