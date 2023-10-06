package com.donabotics.myStore1.rest;

import com.donabotics.myStore1.entity.Product;
import com.donabotics.myStore1.services.AdminServices;
import com.donabotics.myStore1.services.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {
    private AdminServices services;

    @Autowired
    public AdminController(AdminServices services) {
        this.services = services;
    }

    @GetMapping("/admin_home")
    public String showCustomerList(Model model) {
        List<Product> productList = services.listAllProducts();
        model.addAttribute("productList", productList);

        return "admin_home_page";
    }

    @GetMapping("/admin/products")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product());
        return "adminNewProduct";
    }

    @PostMapping("/admin/products")
    private String save(Product product, RedirectAttributes re) {
        services.addProduct(product);
        re.addFlashAttribute("message", "The product has been added successfully!");
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/{id}")
    public String getProduct(@PathVariable("id") Integer id, Model model, RedirectAttributes re) {
        try {
            Product product = services.findById(id);
            model.addAttribute("product", product);

            return "adminProductEdit";
        } catch (CustomerNotFoundException e) {
            re.addFlashAttribute("message", "Product Not Found");
            return "redirect:/admin_home";
        }

    }

    @PutMapping("/admin/products")
    private String saveEdit(Product product, RedirectAttributes re) {
        services.addProduct(product);
        re.addFlashAttribute("message", "Product Edited Successfully!");

        return "redirect:/admin_home";
    }

    @GetMapping("/admin/product/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes re) {
        try {
            Product product = services.findById(id);
            services.removeProduct(product);
            re.addFlashAttribute("message", "Product Deleted Successfully!");

            return "redirect:/admin_home";
        } catch (CustomerNotFoundException e) {
            re.addFlashAttribute("message", "Product Not Found");
            return "redirect:/admin_home";
        }

    }
}
