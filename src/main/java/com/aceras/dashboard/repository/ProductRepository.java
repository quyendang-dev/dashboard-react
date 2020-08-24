package com.aceras.dashboard.repository;

import com.aceras.dashboard.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByCodeContainsAndNameContainsAndDescriptionContainsAndStatusContainsAndTypeContains(String code, String name, String description, String status, String type);
}
