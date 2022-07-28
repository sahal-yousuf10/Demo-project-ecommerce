package com.example.sahal.InventoryService.Controller;

import com.example.sahal.InventoryService.Model.Product;
import com.example.sahal.InventoryService.Service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping(value = "/products", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity<String> saveProductsThroughFile(
            @RequestParam(value = "files") MultipartFile[] files) throws Exception{
        String result = null;
        try {
            for (MultipartFile file : files) {
                result = inventoryService.saveProductsThroughFile(file).get();
            }
        }
        catch (Exception ex){
            log.error("Exception caught "+ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Data not saved due to some internal issue");
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @GetMapping(value = "/product/{name}/{quantity}")
    public ResponseEntity<Product> buyProductByName(
            @PathVariable String name,
            @PathVariable int quantity) throws Exception {
        Product product = new Product();
        try {
            product = inventoryService.findProductByName(name, quantity).get();
        }
        catch (Exception ex){
            log.error("Exception caught "+ex.getMessage());
            throw new Exception("Product with name "+name+" not found due to some internal error!");
        }
        return ResponseEntity.ok(product);
                //.status(HttpStatus.FOUND)
                //.body(product);
    }
}
