package com.example.Productmicroservice.Controllers;

import com.example.Productmicroservice.models.ProductType;
import com.example.Productmicroservice.Services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/api/product-types")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    // Endpoint to create a new product type
    @PostMapping
    public ResponseEntity<ProductType> createProductType(@RequestBody ProductType productType) {
        ProductType newProductType = productTypeService.saveOrUpdateProductType(productType);
        return new ResponseEntity<>(newProductType, HttpStatus.CREATED);
    }

    // Endpoint to retrieve all product types
    @GetMapping
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        return new ResponseEntity<>(productTypes, HttpStatus.OK);
    }

    // Endpoint to retrieve a product type by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductType> getProductTypeById(@PathVariable Long id) {
        return productTypeService.getProductTypeById(id)
                .map(productType -> new ResponseEntity<>(productType, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to update a product type
    @PutMapping("/{id}")
    public ResponseEntity<ProductType> updateProductType(@PathVariable Long id, @RequestBody ProductType productType) {
        productType.setId(id); // Ensure the ID in the request body matches the path variable
        ProductType updatedProductType = productTypeService.saveOrUpdateProductType(productType);
        return new ResponseEntity<>(updatedProductType, HttpStatus.OK);
    }

    // Endpoint to delete a product type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductType(@PathVariable Long id) {
        productTypeService.deleteProductTypeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
