package com.example.productmanager.services;

import com.example.productmanager.entities.Product;

import java.util.List;

public interface ProductCRUDService {
    List<Product> getAll();

    Product getById(int id);

    List<Product> findByValue(String value);

    Product save(Product product);

    void delete(int id);
}
