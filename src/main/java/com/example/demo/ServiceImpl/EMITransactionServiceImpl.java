package com.example.demo.ServiceImpl;

import com.example.demo.Dto.ProductDto;
import com.example.demo.Dto.ProductPurchaseDto;
import com.example.demo.Entity.EMITransaction;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.TenureMonths;
import com.example.demo.Entity.User;
import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Service.IEMITransactionService;
import com.example.demo.Service.IProductService;
import com.example.demo.Service.IUserService;
import com.example.demo.Utils.ResponseBody.ResponseBody;
import com.example.demo.repository.EMITransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        User user = userService.getUserById(productPurchaseDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Invalid User Id: " + productPurchaseDto.getUserId()));

        ProductDto dto = productService.getProductById(productPurchaseDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Invalid Product Id: " + productPurchaseDto.getProductId()));

        Product product= productMapper.convertProductDtoToProduct(dto);


        TenureMonths tenure = TenureMonths.fromValue(productPurchaseDto.getTenureMonths());
        double monthlyInstallment = product.getPrice() / tenure.getValue();

        List<LocalDate> paymentDueDates = new ArrayList<>();
        LocalDate startDate = LocalDate.now();
        for (int i = 1; i <= productPurchaseDto.getTenureMonths(); i++) {
            paymentDueDates.add(startDate.plusMonths(i));
        }
        EMITransaction emiTransaction =new EMITransaction();
        emiTransaction.setUser(user);
        emiTransaction.setProduct(product);
        emiTransaction.setTenure(tenure);
        emiTransaction.setMonthlyInstallment(monthlyInstallment);
        emiTransaction.setStartDate(startDate);
        emiTransaction.setEndDate(startDate.plusMonths(productPurchaseDto.getTenureMonths()));
        emiTransaction.setPaymentDueDates(paymentDueDates);
        emiTransaction.setPaid(false);
        return emiTransactionRepository.save(emiTransaction);
    }

    @Override
    public List<EMITransaction> getUserEMIs(Long userId) {
        return emiTransactionRepository.findByUserId(userId);
    }

}
