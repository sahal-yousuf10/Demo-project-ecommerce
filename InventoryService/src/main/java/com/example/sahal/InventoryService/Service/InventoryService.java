package com.example.sahal.InventoryService.Service;

import com.example.sahal.InventoryService.Exception.ResourceNotFoundException;
import com.example.sahal.InventoryService.Model.Product;
import com.example.sahal.InventoryService.Repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Async
    public CompletableFuture<String> saveProductsThroughFile(MultipartFile file) throws Exception {
        try {
            List<Product> productList = parseCSVFile(file);
            log.info("Saving list of products of size " + productList.size() + " " + Thread.currentThread().getName());
            inventoryRepository.saveAll(productList);
            String message = "Data saved successfully!";
            return CompletableFuture.completedFuture(message);
        }
        catch (Exception ex){
            log.error("Exception caught "+ex.getMessage());
            throw ex;
        }
    }

    private List<Product> parseCSVFile(MultipartFile file) throws Exception{
        final List<Product> productList = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final Product product = new Product();
                    product.setId(Long.parseLong(data[0]));
                    product.setName(data[1]);
                    product.setPrice(Long.parseLong(data[2]));
                    product.setQuantity(Integer.parseInt(data[3]));
                    productList.add(product);
                }
                return productList;
            }
        }
        catch (Exception ex){
            log.error("Failed to parse CSV file "+ex.getMessage());
            throw ex;
        }
    }

    @Async
    public CompletableFuture<Product> findProductByName(String name, int quantity) throws Exception {
        try {
            Product product = inventoryRepository.findByName(name)
                    .filter(x-> x.getQuantity()>=quantity)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with name " + name));
                int remainingQuantity = product.getQuantity();
                product.setQuantity(remainingQuantity - quantity);
                inventoryRepository.save(product);
                return CompletableFuture.completedFuture(product);
        }
        catch (Exception ex){
            log.error("Exception caught "+ex.getMessage());
            throw ex;
        }
    }
}
