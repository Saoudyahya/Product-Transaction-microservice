package com.example.Productmicroservice.repo;


import com.example.Productmicroservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_Repo extends JpaRepository<Product, Long> {

}
