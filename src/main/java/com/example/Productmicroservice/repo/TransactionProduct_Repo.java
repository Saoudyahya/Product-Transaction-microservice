package com.example.Productmicroservice.repo;

import com.example.Productmicroservice.models.TransactionProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionProduct_Repo  extends JpaRepository<TransactionProduct, Long> {
}
