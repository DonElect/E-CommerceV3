package com.donabotics.myStore1.repository;

import com.donabotics.myStore1.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Configuration
//@ComponentScan(basePackages = {"***", "***"})
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    List<Customer> findByEmailAndPassword(String email, String password);
    Customer findByEmailAndPassword(String email, String password);
}
