package com.example.productmanager.controllers;

import com.example.productmanager.entities.Product;
import com.example.productmanager.entities.Retailer;
import com.example.productmanager.services.ProductCRUDService;
import com.example.productmanager.services.ProductDataService;
import com.example.productmanager.services.RetailerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/")
@AllArgsConstructor
public class ProductRestControllerV1 {
    private final ProductCRUDService productCRUDService;
    private final ProductDataService productDataService;
    private final RetailerService retailerService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = this.productCRUDService.getAll();

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductsById(@PathVariable("id") int id) {

        Product product = this.productCRUDService.getById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProductsByValue(@RequestParam("value") String value) {
        List<Product> foundProducts = productCRUDService.findByValue(value);

        if (foundProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(foundProducts, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProducts(@RequestBody Product product) {
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.productCRUDService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProducts(@RequestBody Product product) {
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.productCRUDService.save(product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> deleteProducts(@PathVariable("id") int id) {
        Product product = this.productCRUDService.getById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.productCRUDService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/createTwentyRandomProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> createTwentyRandomProducts() {
        List<Product> createdProducts = this.productDataService.addTwentyRandomProducts();
        if (createdProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(createdProducts, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateProducts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> updateProductsByRetailer(@RequestParam("name") String retailerName) {
        Retailer retailer = retailerService.getByName(retailerName);

        if (retailer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Product> updateProducts = productDataService.updateProductsByRetailer(retailer);

        if (updateProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(updateProducts, HttpStatus.OK);
    }


}
