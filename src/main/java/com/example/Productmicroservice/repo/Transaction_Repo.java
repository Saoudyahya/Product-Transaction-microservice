package com.example.Productmicroservice.repo;

import com.example.Productmicroservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Transaction_Repo  extends JpaRepository<Transaction, Long> {
}
