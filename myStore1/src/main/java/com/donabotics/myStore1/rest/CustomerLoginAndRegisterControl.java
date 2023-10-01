package com.donabotics.myStore1.rest;

import com.donabotics.myStore1.entity.Customer;
import com.donabotics.myStore1.services.CustomerLoginAndRegistrationServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerLoginAndRegisterControl {
    private final CustomerLoginAndRegistrationServices services;

    @Autowired
    public CustomerLoginAndRegisterControl(CustomerLoginAndRegistrationServices services) {
        this.services = services;
    }

    @GetMapping("/customer/login")
    public String loginPage(Model model) {
        model.addAttribute("customer", new Customer());

        return "customerLogin";
    }

    @PostMapping("/customer/login/verification")
    public String verifyLogin(Customer customer, Model model, HttpServletRequest request, RedirectAttributes re) {
        if (services.verifyLogin(customer).isEmpty()) {
            re.addFlashAttribute("message", "Invalid Password or Email!");
            return "redirect:/customer/login";
        }

        Customer verifiedCustomer = services.verifyLogin(customer).get(0);
        model.addAttribute("customerName", customer.getFirstName());
        HttpSession session = request.getSession();
        session.setAttribute("customerId", verifiedCustomer.getId());
        return "redirect:/customer";
    }

    @GetMapping("/customer/register")
    public String registrationPage(Model model) {
        model.addAttribute("customer", new Customer());

        return "customerRegistration";
    }

    @PostMapping("/customer/newCustomer")
    private String saveCustomer(Customer customer) {
        services.addNewCustomer(customer);

        return "redirect:/customer/login";
    }
}
