package com.example.sahal.CustomerService.Feign;

import com.example.sahal.CustomerService.Dto.ErrorDto;
import com.example.sahal.CustomerService.Dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Component
@Slf4j
public class SellerFeignFallback implements SellerFeignService{

    @Override
    public ResponseEntity<ProductDto> buyProductByName(
            @PathVariable String name,
            @PathVariable long CCNo,
            @PathVariable String productName,
            @PathVariable int quantity){
        log.info("Fall back validation");
        ProductDto productDto = new ProductDto();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setTimestamp(new Date());
        errorDto.setDetails("Error");
        errorDto.setMessage("Service down");
        productDto.setErrors(errorDto);
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(productDto);
    }
}
