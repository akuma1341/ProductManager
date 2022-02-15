package com.example.productmanager.services;

import com.example.productmanager.data.ProductCreator;
import com.example.productmanager.entities.Product;
import com.example.productmanager.entities.Retailer;
import com.example.productmanager.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductCRUDService, ProductDataService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCreator productCreator;

    @Override
    @Transactional
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product getById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<Product> findByValue(String value) {
        String theValue = "%" + value + "%";
        return productRepository.findByValue(theValue);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Product> addTwentyRandomProducts() {
        List<Product> createdProducts = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Product product = productCreator.createProduct();
            createdProducts.add(product);
        }

        return productRepository.saveAll(createdProducts);
    }

    @Override
    @Transactional
    public List<Product> updateProductsByRetailer(Retailer retailer) {
        int amount = retailer.getName().equals("RetA") ? 5 : 8;

        List<Product> products = productRepository.findAll();
        List<Product> updatedProducts = products.stream()
                .filter(product -> product.getRetailers().contains(retailer))
                .peek(product -> {
                    int stockLevel = product.getStockLevel();
                    product.setStockLevel(stockLevel + amount);
                })
                .collect(Collectors.toList());
        return productRepository.saveAll(updatedProducts);
    }
}
