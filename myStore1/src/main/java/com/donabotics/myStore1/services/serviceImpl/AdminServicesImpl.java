package com.donabotics.myStore1.services.serviceImpl;

import com.donabotics.myStore1.repository.AdminRepository;
import com.donabotics.myStore1.repository.ProductRepository;
import com.donabotics.myStore1.entity.Admin;
import com.donabotics.myStore1.entity.Product;
import com.donabotics.myStore1.services.AdminLoginAndRegistrationServices;
import com.donabotics.myStore1.services.AdminServices;
import com.donabotics.myStore1.services.CustomerNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServicesImpl implements AdminServices, AdminLoginAndRegistrationServices {
    private final ProductRepository productRepository;
    private final AdminRepository adminRepository;
    private EntityManager entityManager;

    @Autowired
    public AdminServicesImpl(ProductRepository productRepository, AdminRepository adminRepository, EntityManager entityManager) {
        this.productRepository = productRepository;
        this.adminRepository = adminRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Admin addNewAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public List<Product> listAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        product.setCategory(product.getCategory().toUpperCase());
        return productRepository.save(product);
    }

    @Override
    public void removeProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product findById(Integer id) throws CustomerNotFoundException {
        Optional<Product> product = productRepository.findById(id);

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
