package com.donabotics.myStore1.rest;

import com.donabotics.myStore1.entity.Product;
import com.donabotics.myStore1.services.CustomerServicesImpl;
import com.donabotics.myStore1.entity.Customer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {
    private final CustomerServicesImpl services;

    @Autowired
    public CustomerController(CustomerServicesImpl services){
        this.services = services;
    }

    @GetMapping("/customer")
    public String showProduct(Model model){
        List<Product> productList = services.listAll();
        List<String> categories = services.viewByCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("productList", productList);

        return "customerHome";
    }

    @GetMapping("/customer/login")
    public String loginPage(Model model){
        model.addAttribute("customer", new Customer());

        return "customerLogin";
    }

    @PostMapping("/customer/login/verification")
    public String verifyLogin(Customer customer, Model model, HttpServletRequest request){
        Customer verifiedCustomer = services.verifyLogin(customer).get(0);
        if (verifiedCustomer != null){
            model.addAttribute("customerName", customer.getFirstName());
            HttpSession session = request.getSession();
            session.setAttribute("customerId", verifiedCustomer.getId());
            return "redirect:/customer";
        }
        System.out.println("Not verified");
        return "redirect:/customer/login";
    }

    @GetMapping("/customer/register")
    public String registrationPage(Model model){
        model.addAttribute("customer", new Customer());

        return "customerRegistration";
    }

    @PostMapping("/customer/newCustomer")
    private String saveCustomer(Customer customer){
        services.addNewCustomer(customer);

        return "redirect:/customer/login";
    }

    @GetMapping("/customer/addToCart/{id}")
    public String addToCart(@PathVariable("id") Integer product_id, HttpSession session, RedirectAttributes re){
        Integer customer_id = (int) session.getAttribute("customerId");
        re.addFlashAttribute("message", "Added to cart");
        services.addToCart(customer_id, product_id);

        return "redirect:/customer";
    }

    @GetMapping("/customer/cart")
    public String viewCart(HttpSession session, Model model){
        Integer customer_id = (int) session.getAttribute("customerId");
        List<Product> cart = services.viewCart(customer_id);

        model.addAttribute("cart", cart);
        model.addAttribute("count", cart.size());

        return "customerCart";
    }

    @GetMapping("/customer/removeFromCart/{id}")
    public String removeFromCart(@PathVariable("id") Integer product_id, HttpSession session, RedirectAttributes re){
        Integer customer_id = (int) session.getAttribute("customerId");
        services.deleteFromCart(customer_id, product_id);

        return "redirect:/customer/cart";
    }

    @GetMapping("/customer/cart/updateQuantity/{id}")
    private String updateQuantity(@PathVariable("id") Integer signedProductId, HttpSession session){
        Integer customer_id = (int) session.getAttribute("customerId");

        if (signedProductId<0)
            services.updateQuantity(customer_id, -1*signedProductId, -1);
        else
            services.updateQuantity(customer_id, signedProductId, 1);

        return "redirect:/customer/cart";
    }

    @GetMapping("/customer/category/{category}")
    private String viewByCategory(@PathVariable("category") String category, Model model){
        List<Product> productList = services.listByCategory(category);
        List<String> categories = services.viewByCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("productList", productList);

        return "customerHome";
    }

}
