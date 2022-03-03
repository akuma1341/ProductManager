package com.example.productmanager.data;

import com.example.productmanager.entities.Product;
import com.example.productmanager.services.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
public class RandomProductCreator implements ProductCreator{
    @Autowired
    private RetailerService retailerService;

    private static final int LEFT_LIMIT = 97; // letter 'a'
    private static final int RIGHT_LIMIT = 122; // letter 'z'
    private static final int NAME_LENGTH = 30;
    private static final int DESCRIPTION_LENGTH = 100;

    private final Random random = new Random();

    @Override
    public Product createProduct() {
        String randomName = getRandomString(NAME_LENGTH);
        String randomDescription = getRandomString(DESCRIPTION_LENGTH);
        int randomRetailerId = random.nextInt(2) + 1;
        int randomStockLevel = random.nextInt(5);

        Product product = new Product();
        product.setName(randomName);
        product.setDescription(randomDescription);
        product.setStockLevel(randomStockLevel);
        product.addRetailer(retailerService.getById(randomRetailerId));

        return product;
    }

    private String getRandomString(int length) {
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = LEFT_LIMIT + (int) (random.nextFloat() * (RIGHT_LIMIT - LEFT_LIMIT + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}