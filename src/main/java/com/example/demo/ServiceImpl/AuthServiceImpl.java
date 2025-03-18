package com.example.demo.ServiceImpl;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.UserRegisterDto;
import com.example.demo.Entity.Admin;
import com.example.demo.Entity.User;
import com.example.demo.Exceptions.AdminNotFoundException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Service.IAuthService;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String registerUser(UserRegisterDto dto) {
        User user=userMapper.convertingDtoToUser(dto);
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public User loginUser(LoginDto user) {
        User existingUser = userRepository.findByUsername(user.getUsernameOrEmail());
        if (existingUser != null && user.getPassword().equals(existingUser.getPassword())) {
            return existingUser;  // Return user details on successful login
        }
        throw new UserNotFoundException("User not found Invalid cred");  // Return null for invalid credentials
    }
    @Override
    public Admin loginAdmin(LoginDto user) {
        Admin existingAdmin = adminRepository.findByUsername(user.getUsernameOrEmail());
        if (existingAdmin != null && user.getPassword().equals(existingAdmin.getPassword())) {
            return existingAdmin;  // Return admin details on successful login
        }
        throw new AdminNotFoundException("Admin Not found Invalid cred"); // Return null for invalid credentials
    }

}
