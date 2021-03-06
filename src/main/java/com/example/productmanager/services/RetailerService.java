package com.example.productmanager.services;

import com.example.productmanager.entities.Retailer;

import java.util.List;

public interface RetailerService {
    List<Retailer> getAll();

    Retailer getById(int id);

    Retailer getByName(String name);
}
