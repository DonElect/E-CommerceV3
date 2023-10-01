package com.donabotics.myStore1.services;

import com.donabotics.myStore1.entity.Customer;

import java.util.List;

public interface CustomerLoginAndRegistrationServices {
    Customer addNewCustomer(Customer customer);
    List<Customer> verifyLogin(Customer customer);
}
