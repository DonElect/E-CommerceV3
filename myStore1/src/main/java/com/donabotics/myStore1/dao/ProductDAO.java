package com.donabotics.myStore1.dao;

import com.donabotics.myStore1.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends CrudRepository<Product, Integer> {
    public List<Product> findByCategory(String category);
    @Query(value = "SELECT DISTINCT category FROM Product")
    public List<String> viewCategories();
}
