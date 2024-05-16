package com.example.Productmicroservice.Controllers;

import com.example.Productmicroservice.Configuration.MQConfig;
import com.example.Productmicroservice.MessageModel.SupplierMessage;
import com.example.Productmicroservice.models.Product;
import com.example.Productmicroservice.Services.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
    @RequestMapping("/api/products")
public class ProductController {


    private final RestTemplate RestTemplate;
    private final ProductService productService;

    @Autowired
    public ProductController(RestTemplate productRestTemplate, ProductService productService) {
        RestTemplate = productRestTemplate;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.saveOrUpdateProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id); // Ensure the ID in the request body matches the path variable
        Product updatedProduct = productService.saveOrUpdateProduct(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        RestTemplate.delete("http://IMPORT-EXPORT-SERVICE/api/suppliers/notify-product-deletion/{productId}",id);
        RestTemplate.delete("http://IMPORT-EXPORT-SERVICE/api/orders/notify-product-deletion/{productId}", id);
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

   @RabbitListener(queues = MQConfig.IMPORT_EXPORT_QUEUE)
    public void listener(SupplierMessage message){
        System.out.println(message);
   }


}
