package com.example.sahal.SellerService.Feign;


import com.example.sahal.SellerService.Dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-microservice",
        url = "http://localhost:8080",
        fallback = InventoryFeignFallback.class)
public interface InventoryFeignService {

    @GetMapping("/product/{name}/{quantity}")
    ResponseEntity<ProductDto> buyProductByName(
            @PathVariable String name,
            @PathVariable int quantity);
}
