package com.example.Productmicroservice.Controllers;

import com.example.Productmicroservice.Configuration.MQConfig;
import com.example.Productmicroservice.MessageModel.MessageModel;
import com.example.Productmicroservice.models.Product;
import com.example.Productmicroservice.models.ProductType;
import com.example.Productmicroservice.Services.ProductTypeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/api/product-types")
public class ProductTypeController {

    private final ProductTypeService productTypeService;
    @Autowired
    private RabbitTemplate template ;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    // Endpoint to create a new product type
    @PostMapping
    public ResponseEntity<ProductType> createProductType(@RequestBody ProductType productType) {
        ProductType newProductType = productTypeService.saveOrUpdateProductType(productType);
        publishMessage("CREATE", newProductType);
        return new ResponseEntity<>(newProductType, HttpStatus.CREATED);
    }

    // Endpoint to retrieve all product types
    @GetMapping
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        List<ProductType> productTypes = productTypeService.getAllProductTypes();

        return new ResponseEntity<>(productTypes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductType> getProductTypeById(@PathVariable Long id) {
        return productTypeService.getProductTypeById(id)
                .map(productType -> new ResponseEntity<>(productType, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductType> updateProductType(@PathVariable Long id, @RequestBody ProductType productType) {
        productType.setId(id);
        ProductType updatedProductType = productTypeService.saveOrUpdateProductType(productType);
        publishMessage("UPDATE", updatedProductType);
        return new ResponseEntity<>(updatedProductType, HttpStatus.OK);
    }

    // Endpoint to delete a product type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductType(@PathVariable Long id) {

        Optional<ProductType> deletedProductType = productTypeService.getProductTypeById(id);
        deletedProductType.ifPresent(productType -> publishMessage("DELETE", productType));
           productTypeService.deleteProductTypeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void publishMessage(String action, ProductType ProductType) {
        MessageModel message = new MessageModel();
        message.setAction(action);
        message.setMessageId(UUID.randomUUID().toString());
        message.setMessage(" Product: " + ProductType.getName()+" with id:"+ProductType.getId()+" have been "+ action ); // Set the Message field based on the action
        message.setMassageDate(new Date());
        template.convertAndSend(MQConfig.IMPORT_EXPORT_TOPIC_EXCHANGE, MQConfig.ROUTING_KEY, message);
    }
}
