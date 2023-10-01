package com.donabotics.myStore1.services;

import com.donabotics.myStore1.dao.AdminDAO;
import com.donabotics.myStore1.dao.ProductDAO;
import com.donabotics.myStore1.entity.Admin;
import com.donabotics.myStore1.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServicesImpl implements AdminServices{
    private final ProductDAO productDAO;
    private final AdminDAO adminDAO;
    private EntityManager entityManager;

    @Autowired
    public AdminServicesImpl(ProductDAO productDAO, AdminDAO adminDAO, EntityManager entityManager) {
        this.productDAO = productDAO;
        this.adminDAO = adminDAO;
        this.entityManager = entityManager;
    }

    @Override
    public Admin addNewAdmin(Admin admin) {
        return adminDAO.save(admin);
    }

    @Override
    public List<Product> listAllProducts() {
        return (List<Product>) productDAO.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        product.setCategory(product.getCategory().toUpperCase());
        return productDAO.save(product);
    }

    @Override
    public void removeProduct(Product product) {
        productDAO.delete(product);
    }

    @Override
    public Product findById(Integer id) throws CustomerNotFoundException {
        Optional<Product> product = productDAO.findById(id);

        if (product.isEmpty())
            throw new CustomerNotFoundException("Could not find any product with ID " + id);

        return product.get();
    }

    @Override
    public boolean verifyLogin(Admin admin) {
        String email = admin.getEmail();
        String password = admin.getPassword();
        Query query = this.entityManager.createNativeQuery(" SELECT * FROM admins" +
                " WHERE email = ? AND password = ?", Admin.class);
        query.setParameter(1, email);
        query.setParameter(2, password);

        return !query.getResultList().isEmpty();
    }

}
