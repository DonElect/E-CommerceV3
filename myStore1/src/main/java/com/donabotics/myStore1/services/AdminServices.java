package com.donabotics.myStore1.services;

import com.donabotics.myStore1.entity.Admin;
import com.donabotics.myStore1.entity.Product;

import java.util.List;

public interface AdminServices {
    List<Product> listAllProducts();
    Product addProduct(Product product);
    void removeProduct(Product product);
    Product findById(Integer id) throws CustomerNotFoundException;

}
