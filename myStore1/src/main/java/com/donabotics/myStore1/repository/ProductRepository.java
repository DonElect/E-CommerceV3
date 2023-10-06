package com.donabotics.myStore1.repository;

import com.donabotics.myStore1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(String category);
    @Query(value = "SELECT DISTINCT category FROM Product")
    List<String> viewCategories();
    List<Product> findByProdName(String productName);
}
