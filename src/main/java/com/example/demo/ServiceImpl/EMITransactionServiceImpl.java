package com.example.demo.ServiceImpl;

import com.example.demo.Dto.ProductDto;
import com.example.demo.Dto.ProductPurchaseDto;
import com.example.demo.Entity.*;
import com.example.demo.Exceptions.InsufficientBalanceException;
import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Service.IEMITransactionService;
import com.example.demo.Service.IProductService;
import com.example.demo.Service.IUserService;
import com.example.demo.repository.EMITransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EMITransactionServiceImpl implements IEMITransactionService {

    @Autowired
    private EMITransactionRepository emiTransactionRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    @Override
    public EMITransaction purchaseProduct(ProductPurchaseDto productPurchaseDto) {

        // Fetch user and product
        User user = userService.getUserById(productPurchaseDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Invalid User ID: " + productPurchaseDto.getUserId()));

        ProductDto productDto = productService.getProductById(productPurchaseDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Invalid Product ID: " + productPurchaseDto.getProductId()));

        Product product = productMapper.convertProductDtoToProduct(productDto);
        // Get user’s card
        Card userCard = user.getCard();
        if (userCard == null) {
            throw new RuntimeException("User does not have an assigned EMI card.");
        }

        // Check if the card has enough balance
        if (userCard.getRemainingLimit() < product.getPrice()) {
            throw new InsufficientBalanceException("Insufficient card balance to purchase this product.");
        }

        // Deduct the amount from the card limit
        int updatedLimit = userCard.getRemainingLimit() - (int) product.getPrice();
        userCard.setRemainingLimit(updatedLimit);

        // Convert tenure from int to Enum
        TenureMonths tenure = TenureMonths.fromValue(productPurchaseDto.getTenureMonths());

        // Calculate EMI details
        double monthlyInstallment = product.getPrice() / tenure.getValue();

        // Generate payment due dates
        List<LocalDate> paymentDueDates = new ArrayList<>();
        LocalDate startDate = LocalDate.now();
        for (int i = 1; i <= tenure.getValue(); i++) {
            paymentDueDates.add(startDate.plusMonths(i));
        }

        // Create EMI Transaction
        EMITransaction emiTransaction = new EMITransaction(
                null, user, product, tenure, monthlyInstallment, startDate,
                startDate.plusMonths(tenure.getValue()), paymentDueDates, false
        );

        // Save transaction and update card balance
        emiTransactionRepository.save(emiTransaction);
        userService.updateUser(user.getId(), user);  // Save updated card limit

        return emiTransaction;
    }

    @Override
    public List<EMITransaction> getUserEMIs(Long userId) {
        return emiTransactionRepository.findByUserId(userId);
    }
}
