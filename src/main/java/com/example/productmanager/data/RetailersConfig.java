package com.example.productmanager.data;

import com.example.productmanager.entities.Retailer;
import com.example.productmanager.repositories.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetailersConfig implements RetailersCreator {
    @Autowired
    private RetailerRepository retailerRepository;

    @Override
    public void createRetailersIfNeeded() {
        Retailer retailerA = retailerRepository.findByName("RetA").orElse(null);
        Retailer retailerB = retailerRepository.findByName("RetB").orElse(null);
        if (retailerA == null) {
            Retailer createdRetailerA = new Retailer();
            createdRetailerA.setName("RetA");
            retailerRepository.save(createdRetailerA);
        }
        if (retailerB == null) {
            Retailer createdRetailerB = new Retailer();
            createdRetailerB.setName("RetB");
            retailerRepository.save(createdRetailerB);
        }
    }
}
