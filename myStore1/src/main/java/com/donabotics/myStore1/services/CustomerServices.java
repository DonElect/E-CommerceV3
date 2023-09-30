package com.donabotics.myStore1.services;

import com.donabotics.myStore1.dao.CustomerDAO;
import com.donabotics.myStore1.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServices{
    private CustomerDAO repo;

    @Autowired
    public CustomerServices(CustomerDAO repo){
        this.repo = repo;
    }

    public List<Customer> listAll(){
        return (List<Customer>) repo.findAll();
    }

}
