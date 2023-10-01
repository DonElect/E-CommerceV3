package com.donabotics.myStore1.services;

import com.donabotics.myStore1.entity.Cart;
import com.donabotics.myStore1.entity.Product;

import java.util.List;

public interface CustomerServices {
    List<Product> listAll();
    int addToCart(Integer customerId, Integer productId);
    int updateQuantity(Integer customer_id, Integer product_id, Integer quantity);
    List<String> viewByCategory();
    List<Product> listByCategory(String category);
    int deleteFromCart(Integer customer_id, Integer product_id);
    List<Product> viewCart(Integer customer_id);
    List<Product> searchFor(String prodName);
}
