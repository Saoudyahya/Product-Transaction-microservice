package com.example.Productmicroservice.models;


import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String Description;

    private long Stock;

    private String Reference;

    private String Brand;

    private double price_Unit;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;


    public Product(Long id, String name, String description, long stock, String reference, double price_Unit, ProductType productType,String Brand) {
        this.id = id;
        this.name = name;
        this.Brand= Brand;
        Description = description;
        Stock = stock;
        Reference = reference;
        this.price_Unit = price_Unit;
        this.productType = productType;
    }

    public Product() {

    }
    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public long getStock() {
        return Stock;
    }

    public void setStock(long stock) {
        Stock = stock;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    public double getPrice_Unit() {
        return price_Unit;
    }

    public void setPrice_Unit(double price_Unit) {
        this.price_Unit = price_Unit;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}