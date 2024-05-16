package com.example.Productmicroservice.Controllers;

import com.example.Productmicroservice.models.TransactionProduct;
import com.example.Productmicroservice.Services.TransactionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction-products")
public class TransactionProductController {

    private final TransactionProductService transactionProductService;

    @Autowired
    public TransactionProductController(TransactionProductService transactionProductService) {
        this.transactionProductService = transactionProductService;
    }

    // Endpoint to create a new transaction product
    @PostMapping
    public ResponseEntity<TransactionProduct> createTransactionProduct(@RequestBody TransactionProduct transactionProduct) {
        TransactionProduct newTransactionProduct = transactionProductService.saveOrUpdateTransactionProduct(transactionProduct);
        return new ResponseEntity<>(newTransactionProduct, HttpStatus.CREATED);
    }

    // Endpoint to retrieve all transaction products
    @GetMapping
    public ResponseEntity<List<TransactionProduct>> getAllTransactionProducts() {
        List<TransactionProduct> transactionProducts = transactionProductService.getAllTransactionProducts();
        return new ResponseEntity<>(transactionProducts, HttpStatus.OK);
    }

    // Endpoint to retrieve a transaction product by ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionProduct> getTransactionProductById(@PathVariable Long id) {
        return transactionProductService.getTransactionProductById(id)
                .map(transactionProduct -> new ResponseEntity<>(transactionProduct, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to update a transaction product
    @PutMapping("/{id}")
    public ResponseEntity<TransactionProduct> updateTransactionProduct(@PathVariable Long id, @RequestBody TransactionProduct transactionProduct) {
        transactionProduct.setId(id); // Ensure the ID in the request body matches the path variable
        TransactionProduct updatedTransactionProduct = transactionProductService.saveOrUpdateTransactionProduct(transactionProduct);
        return new ResponseEntity<>(updatedTransactionProduct, HttpStatus.OK);
    }

    // Endpoint to delete a transaction product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionProduct(@PathVariable Long id) {
        transactionProductService.deleteTransactionProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
