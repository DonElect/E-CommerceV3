package com.donabotics.myStore1.rest;

import com.donabotics.myStore1.services.CustomerServices;
import com.donabotics.myStore1.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {
    private CustomerServices services;

    @Autowired
    public CustomerController(CustomerServices services){
        this.services = services;
    }

    @GetMapping("/customers")
    public String showCustomerList(Model model){
        List<Customer> customerList = services.listAll();
        model.addAttribute("customerList", customerList);

        return "customers";
    }

//    @GetMapping"/login"
//    public
}
