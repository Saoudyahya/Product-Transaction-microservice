package com.example.Productmicroservice.Controllers;

import com.example.Productmicroservice.Configuration.MQConfig;
import com.example.Productmicroservice.MessageModel.MessageModel;
import com.example.Productmicroservice.models.Product;
import com.example.Productmicroservice.Services.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
    @RequestMapping("/api/products")
public class ProductController {


    private final RestTemplate RestTemplate;
    private final ProductService productService;
    @Autowired
    private RabbitTemplate template ;


    @Autowired
    public ProductController(RestTemplate productRestTemplate, ProductService productService) {
        RestTemplate = productRestTemplate;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.saveOrUpdateProduct(product);
        publishMessage("CREATE", newProduct);
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
        product.setId(id);

        Product updatedProduct = productService.saveOrUpdateProduct(product);
        publishMessage("UPDATE", updatedProduct);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        RestTemplate.delete("http://IMPORT-EXPORT-SERVICE/api/suppliers/notify-product-deletion/{productId}",id);
        RestTemplate.delete("http://IMPORT-EXPORT-SERVICE/api/orders/notify-product-deletion/{productId}", id);
        Optional<Product> deletedProduct = productService.getProductById(id);
        deletedProduct.ifPresent(product -> publishMessage("DELETE", product));
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }   


    private void publishMessage(String action, Product product) {
        MessageModel message = new MessageModel();
        message.setAction(action);
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessage(" Product: " + product.getName()+" with id:"+product.getId()+" Reference: "+product.getReference()+" have been "+ action ); // Set the Message field based on the action
        message.setMassageDate(new Date());
        template.convertAndSend(MQConfig.IMPORT_EXPORT_TOPIC_EXCHANGE, MQConfig.ROUTING_KEY, message);
    }


}
