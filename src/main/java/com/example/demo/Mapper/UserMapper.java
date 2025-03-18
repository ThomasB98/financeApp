package com.example.demo.Mapper;

import com.example.demo.Dto.UserRegisterDto;
import com.example.demo.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User convertingDtoToUser(UserRegisterDto dto){
        User user = new User();
        user.setName(dto.getName());
        user.setDOB(dto.getDob());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAddress(dto.getAddress());
        user.setBankName(dto.getBankName());
        user.setAccountNumber(dto.getAccountNumber());
        user.setIfscCode(dto.getIfscCode());
        return user;
    }
}
