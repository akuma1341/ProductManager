package com.example.productmanager.repositories;

import com.example.productmanager.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name like :value or p.description like :value")
    List<Product> findByValue(@Param("value") String value);
}
