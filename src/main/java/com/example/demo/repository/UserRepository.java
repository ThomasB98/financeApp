package com.example.demo.repository;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    boolean existsByEmailOrAccountNumberOrUsername(String email, String accountNumber, String username);
}
