package com.example.demo.repository;

import com.example.demo.Entity.EMITransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EMITransactionRepository extends JpaRepository<EMITransaction,Long> {
    List<EMITransaction> findByUserId(Long userId);
}
