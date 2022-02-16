package com.example.productmanager.data;

import com.example.productmanager.entities.Product;
import com.example.productmanager.services.ProductCRUDService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddingNewProductsThread extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddingNewProductsThread.class);
    private final int pause = 300_000;

    @Autowired
    private ProductCreator productCreator;

    @Autowired
    private ProductCRUDService productCRUDService;

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 3; i++) {
                Product product = productCreator.createProduct();
                productCRUDService.save(product);
                LOGGER.info("Automatically created product: " + product);
            }
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                LOGGER.error("AddingNewProductsTread interrupted:\n" + e.getMessage());
            }
        }
    }
}
