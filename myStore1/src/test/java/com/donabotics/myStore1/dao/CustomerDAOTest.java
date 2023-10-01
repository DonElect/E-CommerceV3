package com.donabotics.myStore1.dao;

import com.donabotics.myStore1.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(loader= AnnotationConfigContextLoader.class, classes = CustomerDAO.class)
@Rollback(false)
@Configuration
public class CustomerDAOTest {
    @Autowired private CustomerDAO repo;


    @Test
    public void testAddNew(){
        Customer customer = new Customer();

        customer.setFirstName("Mike");
        customer.setLastName("John");
        customer.setEmail("luke@gmail.com");
        customer.setPassword("john1234");
        customer.setPhoneNumber("0812345948");
        customer.setAddress("Calabar street");

        repo.save(customer);

        Assertions.assertNotNull(customer);
        Assertions.assertTrue(customer.getId()>0);
    }
}