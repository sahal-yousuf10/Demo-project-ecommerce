package com.example.sahal.CustomerService.Controller;

import com.example.sahal.CustomerService.Dto.ProductDto;
import com.example.sahal.CustomerService.Service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/product")
    public ResponseEntity<ProductDto> buyProductByName(
            @RequestParam(value = "Name") String name,
            @RequestParam(value = "CCNo") long CCNo,
            @RequestParam(value = "Product Name") String productName,
            @RequestParam(value = "Quantity") int quantity) throws Exception {
        ProductDto productDto = new ProductDto();
        try {
            productDto = customerService.buyProductByName(name, CCNo, productName, quantity).get();
        }
        catch (Exception ex){
            log.error("Exception caught "+ex.getMessage());
            throw new Exception("Product with name "+productName+" not found due to some internal error!");
        }
        return ResponseEntity.ok(productDto);
                //.status(HttpStatus.FOUND)
                //.body(productDto);
    }
}
