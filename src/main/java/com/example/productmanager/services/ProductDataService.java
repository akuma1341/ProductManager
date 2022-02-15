package com.example.productmanager.services;

import com.example.productmanager.entities.Product;
import com.example.productmanager.entities.Retailer;

import java.util.List;

public interface ProductDataService {
    List<Product> addTwentyRandomProducts();

    List<Product> updateProductsByRetailer(Retailer retailer);
}
