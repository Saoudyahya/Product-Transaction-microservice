package com.example.Productmicroservice.models;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private List<TransactionProduct> products;
    @Column(name = "transaction_Type")
    private String transactionType;


    @Column(name = "transaction_Date")
    private Date transactionDate;

    @Column(name = "transaction_client")
    private Integer client;

    @Column(name = "transaction_payment_method")
    private String payment_method;

    @Column(name = "transaction_Total_price")
    private double totalPrice;

    @Column(name = "transaction_References")
        private String References;



    public Transaction(Long id, List<TransactionProduct> products, String transactionType, Date transactionDate, Integer client, String payment_method, double totalPrice, String References) {
        this.id = id;
        this.products = products;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.client = client;
        this.payment_method = payment_method;
        this.totalPrice = totalPrice;
        this.References=References ;
    }


    public List<TransactionProduct> getProducts() {
        return products;
    }

    public void setProducts(List<TransactionProduct> products) {
        this.products = products;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Transaction() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferences() {
        return References;
    }

    public void setReferences(String references) {
        References = references;
    }


    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}