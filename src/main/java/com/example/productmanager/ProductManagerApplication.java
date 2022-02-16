package com.example.productmanager;

import com.example.productmanager.data.AddingNewProductsThread;
import com.example.productmanager.data.RetailersCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProductManagerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ProductManagerApplication.class, args);

        RetailersCreator retailersCreator = context.getBean(RetailersCreator.class);
        retailersCreator.createRetailersIfNeeded();

        AddingNewProductsThread addingNewProductsThread = context.getBean(AddingNewProductsThread.class);
        addingNewProductsThread.start();
    }

}
