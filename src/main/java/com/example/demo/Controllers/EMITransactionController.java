package com.example.demo.Controllers;

import com.example.demo.Dto.ProductPurchaseDto;
import com.example.demo.Entity.EMITransaction;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.User;
import com.example.demo.Service.IEMITransactionService;
import com.example.demo.Service.IProductService;
import com.example.demo.Service.IUserService;
import com.example.demo.Utils.ResponseBody.ResponseBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emi")
public class EMITransactionController {
    @Autowired
    private IEMITransactionService emiTransactionService;

    @PostMapping("/purchase/")
    public ResponseEntity<ResponseBody<EMITransaction>> purchaseProduct(@Valid @RequestBody ProductPurchaseDto productPurchaseDto) {

        EMITransaction emiTransaction = emiTransactionService.purchaseProduct(productPurchaseDto);
        return new ResponseEntity<>(new ResponseBody<>("Product purchased successfully!",
                true, HttpStatus.CREATED, emiTransaction), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseBody<List<EMITransaction>>> getUserEMIs(@PathVariable Long userId) {
        List<EMITransaction> emiTransactions = emiTransactionService.getUserEMIs(userId);

        return new ResponseEntity<>(new ResponseBody<>("EMI details fetched",
                true, HttpStatus.OK, emiTransactions), HttpStatus.OK);
    }
}
