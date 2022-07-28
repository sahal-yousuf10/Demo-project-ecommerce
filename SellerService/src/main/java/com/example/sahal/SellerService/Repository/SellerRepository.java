package com.example.sahal.SellerService.Repository;

import com.example.sahal.SellerService.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}
