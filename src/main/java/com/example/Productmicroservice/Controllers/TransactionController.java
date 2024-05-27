package com.example.Productmicroservice.Controllers;

import com.example.Productmicroservice.Configuration.MQConfig;
import com.example.Productmicroservice.MessageModel.MessageModel;
import com.example.Productmicroservice.models.Transaction;
import com.example.Productmicroservice.Services.TransactionService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    @Autowired
    private RabbitTemplate template ;
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Endpoint to create a new transaction
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.saveOrUpdateTransaction(transaction);
        publishMessage("CREATE", newTransaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    // Endpoint to retrieve all transactions
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // Endpoint to retrieve a transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(transaction -> new ResponseEntity<>(transaction, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to update a transaction
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        transaction.setId(id); // Ensure the ID in the request body matches the path variable
        Transaction updatedTransaction = transactionService.saveOrUpdateTransaction(transaction);
        publishMessage("CREATE", updatedTransaction);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }

    // Endpoint to delete a transaction
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        Optional<Transaction> deletedTransaction = transactionService.getTransactionById(id);
        deletedTransaction.ifPresent(Transaction -> publishMessage("DELETE", Transaction));
        transactionService.deleteTransactionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void publishMessage(String action, Transaction Transaction) {
        MessageModel message = new MessageModel();
        message.setAction(action);
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessage("Transaction  References: " + Transaction.getReferences()+" with id:"+Transaction.getId()+" have been "+ action ); // Set the Message field based on the action
        message.setMassageDate(new Date());
        template.convertAndSend(MQConfig.IMPORT_EXPORT_TOPIC_EXCHANGE, MQConfig.ROUTING_KEY, message);
    }
}
