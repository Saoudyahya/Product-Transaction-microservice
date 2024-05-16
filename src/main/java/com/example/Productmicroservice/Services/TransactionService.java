package com.example.Productmicroservice.Services;

import com.example.Productmicroservice.models.Transaction;
import com.example.Productmicroservice.repo.Transaction_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final Transaction_Repo transactionRepository;

    @Autowired
    public TransactionService(Transaction_Repo transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Method to save or update a transaction
    public Transaction saveOrUpdateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Method to retrieve all transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Method to retrieve a transaction by its ID
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    // Method to delete a transaction by its ID
    public void deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
    }

    // You can add more business logic methods as needed
}
