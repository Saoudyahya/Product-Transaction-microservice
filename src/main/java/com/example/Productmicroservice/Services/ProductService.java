package com.example.Productmicroservice.Services;

import com.example.Productmicroservice.models.Product;
import com.example.Productmicroservice.repo.Product_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final Product_Repo productRepository;

    @Autowired
    public ProductService(Product_Repo productRepository) {
        this.productRepository = productRepository;
    }

    // Method to save or update a product
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Method to retrieve a product by its ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Method to delete a product by its ID
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    // You can add more business logic methods as needed
}
