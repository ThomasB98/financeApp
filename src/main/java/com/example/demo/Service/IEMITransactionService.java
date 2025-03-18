package com.example.demo.Service;

import com.example.demo.Dto.ProductPurchaseDto;
import com.example.demo.Entity.EMITransaction;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;

import java.util.List;
import java.util.Optional;

public interface IEMITransactionService {
    EMITransaction purchaseProduct(ProductPurchaseDto productPurchaseDto);
    List<EMITransaction> getUserEMIs(Long userId);
}
