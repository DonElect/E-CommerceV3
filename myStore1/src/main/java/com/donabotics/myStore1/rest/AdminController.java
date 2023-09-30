package com.donabotics.myStore1.rest;

import com.donabotics.myStore1.entity.Admin;
import com.donabotics.myStore1.entity.Product;
import com.donabotics.myStore1.services.AdminServices;
import com.donabotics.myStore1.services.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {
    private AdminServices services;

    @Autowired
    public AdminController(AdminServices services) {
        this.services = services;
    }

    @GetMapping("/admin/register")
    public String register(Model model) {
        model.addAttribute("admin", new Admin());

        return "adminRegistration";
    }

    @PostMapping("/admin/newAdmin")
    private String addAdmin(Admin admin, RedirectAttributes re) {
        services.addNewAdmin(admin);
        re.addFlashAttribute("message", "Welcome to Don Feast " + admin.getFirstName());

        return "redirect:/admin/login";
    }

    @GetMapping("/admin/login")
    public String loginPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "adminLogin";
    }

    @GetMapping("/admin/login/verification")
    public String verify(Admin admin) {
        assert admin != null;
        return services.verifyLogin(admin) ? "redirect:/admin" : "adminLogin";
    }

    @GetMapping("/admin")
    public String showCustomerList(Model model) {
        List<Product> productList = services.listAllProducts();
        model.addAttribute("productList", productList);

        return "admin";
    }

    @GetMapping("/admin/addNewProduct")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product());
        return "adminNewProduct";
    }

    @PostMapping("/admin/addNewProduct/save")
    private String save(Product product, RedirectAttributes re) {
        services.addProduct(product);
        re.addFlashAttribute("message", "The product has been added successfully!");
        return "redirect:/admin/addNewProduct";
    }

    @GetMapping("/admin/edit/{id}")
    public String getProduct(@PathVariable("id") Integer id, Model model, RedirectAttributes re) {
        try {
            Product product = services.findById(id);
            model.addAttribute("product", product);

            return "adminProductEdit";
        } catch (CustomerNotFoundException e) {
            re.addFlashAttribute("message", "Product Not Found");
            return "redirect:/admin";
        }

    }

    @PostMapping("/admin/editProduct/save")
    private String saveEdit(Product product, RedirectAttributes re) {
        services.addProduct(product);
        re.addFlashAttribute("message", "Product Edited Successfully!");

        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes re) {
        try {
            Product product = services.findById(id);
            services.removeProduct(product);
            re.addFlashAttribute("message", "Product Deleted Successfully!");

            return "redirect:/admin";
        } catch (CustomerNotFoundException e) {
            re.addFlashAttribute("message", "Product Not Found");
            return "redirect:/admin";
        }

    }
}
