package com.donabotics.myStore1.dao;

import com.donabotics.myStore1.entity.Customer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Configuration
@ComponentScan(basePackages = {"***", "***"})
@Repository
public interface CustomerDAO extends CrudRepository<Customer, Integer> {

}