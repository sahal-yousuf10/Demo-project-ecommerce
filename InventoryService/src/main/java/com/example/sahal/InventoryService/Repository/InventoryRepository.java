package com.example.sahal.InventoryService.Repository;

import com.example.sahal.InventoryService.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
