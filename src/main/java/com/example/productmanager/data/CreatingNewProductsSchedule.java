package com.example.productmanager.data;

import com.example.productmanager.entities.Product;
import com.example.productmanager.services.ProductCRUDService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CreatingNewProductsSchedule {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreatingNewProductsSchedule.class);
//    private final int pause = 300_000;

    @Autowired
    private ProductCreator productCreator;

    @Autowired
    private ProductCRUDService productCRUDService;

    @Scheduled(cron = "0 */5 * * * *")
    public void createThreeNewProducts() {
            for (int i = 0; i < 3; i++) {
                Product product = productCreator.createProduct();
                productCRUDService.save(product);
                LOGGER.info("Automatically created product: " + product);
            }
    }
}
