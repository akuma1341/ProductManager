package com.example.productmanager.repositories;

import com.example.productmanager.entities.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RetailerRepository extends JpaRepository<Retailer, Integer> {
    Optional<Retailer> findByName(String name);
}
