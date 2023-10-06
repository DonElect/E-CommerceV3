package com.donabotics.myStore1.services.serviceImpl;

import com.donabotics.myStore1.repository.CartRepository;
import com.donabotics.myStore1.repository.CustomerRepository;
import com.donabotics.myStore1.repository.ProductRepository;
import com.donabotics.myStore1.entity.Cart;
import com.donabotics.myStore1.entity.Customer;
import com.donabotics.myStore1.entity.Product;
import com.donabotics.myStore1.services.CustomerLoginAndRegistrationServices;
import com.donabotics.myStore1.services.CustomerServices;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServicesImpl implements CustomerServices, CustomerLoginAndRegistrationServices {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final EntityManager entityManager;
    private final CartRepository cartRepository;

    @Autowired
    public CustomerServicesImpl(ProductRepository productRepository, CustomerRepository customerRepository,
                                EntityManager entityManager, CartRepository cartRepository){
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.entityManager = entityManager;
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Product> listAll(){
        return (List<Product>) productRepository.findAll();
    }

    @Transactional
    @Override
    public int addToCart(Integer customerId, Integer productId) {
        return entityManager.createNativeQuery("call add_to_cart(?, ?)", Cart.class)
                .setParameter(1, customerId)
                .setParameter(2, productId)
                .executeUpdate();
    }

    @Transactional
    @Override
    public int updateQuantity(Integer customer_id, Integer product_id, Integer quantity) {
        return entityManager.createNativeQuery("call update_cart(?, ?, ?)", Cart.class)
                .setParameter(1, customer_id)
                .setParameter(2, product_id)
                .setParameter(3, quantity)
                .executeUpdate();
    }

    @Override
    public List<String> viewByCategory() {
        return productRepository.viewCategories();
    }

    @Override
    public List<Product> listByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Transactional
    @Override
    public int deleteFromCart(Integer customer_id, Integer product_id) {
        return entityManager.createNativeQuery("call remove_from_cart(?, ?)", Cart.class)
                .setParameter(1, customer_id)
                .setParameter(2, product_id)
                .executeUpdate();
    }

    @Override
    public List<Product> viewCart(Integer customer_id) {
        return entityManager.createNativeQuery("SELECT * FROM view_cart(?)", Product.class)
                .setParameter(1, customer_id)
                .getResultList();
    }

    @Override
    public List<Product> searchFor(String prodName) {
        return entityManager.createNativeQuery("SELECT * FROM search_for(?)", Product.class)
                .setParameter(1, prodName)
                .getResultList();
    }

    @Override
    public Customer addNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer verifyLogin(Customer customer) {
        return customerRepository.findByEmailAndPassword(customer.getEmail(), customer.getPassword());
    }
}
