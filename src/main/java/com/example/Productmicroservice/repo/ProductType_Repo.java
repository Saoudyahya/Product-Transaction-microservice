package com.example.Productmicroservice.repo;

import com.example.Productmicroservice.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductType_Repo  extends JpaRepository<ProductType, Long> {
}
