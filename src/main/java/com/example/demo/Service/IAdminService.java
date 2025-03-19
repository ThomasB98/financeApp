package com.example.demo.Service;

import org.springframework.stereotype.Service;


public interface IAdminService {
    String verifyUser(Long id);
    String verifyCard(Long id);
}
