    package com.example.Productmicroservice.models;
    
    import jakarta.persistence.*;
    
    
    
    
    @Entity
    public class TransactionProduct {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;
    
        @Column(name = "transaction_Total_price")
        private Integer Total_price;
    
    
    
    
        @Column(name = "product_quantity")
        private int quantity;
    
    
    
        public TransactionProduct(Long id, Product product, Integer total_price) {
    
            this.id = id;
            this.product = product;
            this.Total_price = total_price;
    
        }
    
    
    
        public TransactionProduct() {
    
        }
    
    
    
    
        public Long getId() {
            return id;
        }
    

    
        public Product getProduct() {
            return product;
        }
    
    
        public void setId(Long id) {
            this.id = id;
        }
    

    
        public void setProduct(Product product) {
            this.product = product;
        }
        public Integer getTotal_price() {
            return Total_price;
        }
    
        public void setTotal_price(Integer total_price) {
            Total_price = total_price;
        }
    
        public int getQuantity() {
            return quantity;
        }
    
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    
    }