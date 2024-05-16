package com.example.Productmicroservice.Services;

import com.example.Productmicroservice.models.ProductType;
import com.example.Productmicroservice.repo.ProductType_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {

    private final ProductType_Repo productTypeRepository;

    @Autowired
    public ProductTypeService(ProductType_Repo productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    // Method to save or update a product type
    public ProductType saveOrUpdateProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    // Method to retrieve all product types
    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    // Method to retrieve a product type by its ID
    public Optional<ProductType> getProductTypeById(Long id) {
        return productTypeRepository.findById(id);
    }

    // Method to delete a product type by its ID
    public void deleteProductTypeById(Long id) {
        productTypeRepository.deleteById(id);
    }

    // You can add more business logic methods as needed
}
