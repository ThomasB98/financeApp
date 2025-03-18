package com.example.demo.Service;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.UserRegisterDto;
import com.example.demo.Entity.Admin;
import com.example.demo.Entity.User;
import org.springframework.stereotype.Service;


public interface IAuthService {
    String registerUser(UserRegisterDto userRegisterDto);
    User loginUser(LoginDto user);
    Admin loginAdmin(LoginDto user);
}
