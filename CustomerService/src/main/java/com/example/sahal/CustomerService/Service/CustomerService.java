package com.example.sahal.CustomerService.Service;

import com.example.sahal.CustomerService.Dto.ErrorDto;
import com.example.sahal.CustomerService.Dto.ProductDto;
import com.example.sahal.CustomerService.Feign.SellerFeignService;
import com.example.sahal.CustomerService.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SellerFeignService sellerFeignService;

    @Async
    public CompletableFuture<ProductDto> buyProductByName(String name, long CCNo, String productName, int quantity) {
        CompletableFuture completableFuture = new CompletableFuture();
        try {
            ProductDto productDto = sellerFeignService.buyProductByName(name, CCNo, productName, quantity).getBody();
            if (productDto != null && productDto.getErrors() == null) {
                return completableFuture.completedFuture(productDto);
            }
            else if (productDto.getErrors() != null) {
                ProductDto productDto1 = new ProductDto();
                ErrorDto error = new ErrorDto();
                error.setTimestamp(productDto1.getErrors().getTimestamp());
                error.setMessage(productDto1.getErrors().getMessage());
                error.setDetails(productDto1.getErrors().getDetails());
                productDto1.setErrors(error);
                return completableFuture.completedFuture(productDto1);
            }
        }
        catch (Exception ex){
            log.error("Exception caught "+ex.getMessage());
            throw ex;
        }
        return completableFuture;
    }
}
