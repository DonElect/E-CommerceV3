package com.donabotics.myStore1.services;

import com.donabotics.myStore1.entity.Admin;
import com.donabotics.myStore1.entity.Product;

import java.util.List;

public interface AdminServices {
    Admin addNewAdmin(Admin admin);
    List<Product> listAllProducts();
    Product addProduct(Product product);
    void removeProduct(Product product);
    Product findById(Integer id) throws CustomerNotFoundException;
    boolean verifyLogin(Admin admin);
}
