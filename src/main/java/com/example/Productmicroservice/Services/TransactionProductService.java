package com.example.Productmicroservice.Services;

import com.example.Productmicroservice.models.TransactionProduct;
import com.example.Productmicroservice.repo.TransactionProduct_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionProductService {

    private final TransactionProduct_Repo transactionProductRepository;

    @Autowired
    public TransactionProductService(TransactionProduct_Repo transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }


    public TransactionProduct saveOrUpdateTransactionProduct(TransactionProduct transactionProduct) {
        return transactionProductRepository.save(transactionProduct);
    }


    public List<TransactionProduct> getAllTransactionProducts() {
        return transactionProductRepository.findAll();
    }


    public Optional<TransactionProduct> getTransactionProductById(Long id) {
        return transactionProductRepository.findById(id);
    }

    public void deleteTransactionProductById(Long id) {
        transactionProductRepository.deleteById(id);
    }


}
