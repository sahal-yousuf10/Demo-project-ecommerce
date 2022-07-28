package com.example.sahal.CustomerService.Feign;


import com.example.sahal.CustomerService.Dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "seller-microservice",
        url = "http://localhost:8082",
        fallback = SellerFeignFallback.class)
public interface SellerFeignService {

    @GetMapping("/product/{name}/{CCNo}/{productName}/{quantity}")
    ResponseEntity<ProductDto> buyProductByName(
            @PathVariable String name,
            @PathVariable long CCNo,
            @PathVariable String productName,
            @PathVariable int quantity);
}
