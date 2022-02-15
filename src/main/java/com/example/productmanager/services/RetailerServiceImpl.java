package com.example.productmanager.services;

import com.example.productmanager.entities.Retailer;
import com.example.productmanager.repositories.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RetailerServiceImpl implements RetailerService{
    @Autowired
    private RetailerRepository retailerRepository;

    @Override
    @Transactional
    public List<Retailer> getAll() {
        return retailerRepository.findAll();
    }

    @Override
    @Transactional
    public Retailer getById(int id) {
        return retailerRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Retailer getByName(String name) {
        return retailerRepository.findByName(name).orElse(null);
    }
}
