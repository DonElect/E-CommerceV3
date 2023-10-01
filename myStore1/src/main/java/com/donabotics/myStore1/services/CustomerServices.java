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
//    @Query("SELECT c.productId,p.category,p.prodName,c.quantity,p.unitPrice FROM Cart c\n" +
//            "    JOIN Product p\n" +
//            "    ON p.id = c.productId\n" +
//            "    WHERE c.customerId = :cust_id")
    List<Product> viewCart(Integer customer_id);
}
