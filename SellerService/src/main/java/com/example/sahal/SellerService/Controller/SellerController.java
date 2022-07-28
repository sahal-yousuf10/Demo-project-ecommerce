package com.example.sahal.SellerService.Controller;

import com.example.sahal.SellerService.Dto.ProductDto;
import com.example.sahal.SellerService.Service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/product/{name}/{CCNo}/{productName}/{quantity}")
    ResponseEntity<ProductDto> buyProductByName(
            @PathVariable String name,
            @PathVariable long CCNo,
            @PathVariable String productName,
            @PathVariable int quantity) throws Exception {
        ProductDto productDto = new ProductDto();
        try {
            productDto = sellerService.buyProductByName(name, CCNo, productName, quantity).get();
        }
        catch (Exception ex){
            log.error("Exception caught "+ex.getMessage());
            throw new Exception("Product with name "+name+" not found due to some internal error!");
        }
        return ResponseEntity.ok(productDto);
                //.status(HttpStatus.FOUND)
                //.body(productDto);
    }
}
