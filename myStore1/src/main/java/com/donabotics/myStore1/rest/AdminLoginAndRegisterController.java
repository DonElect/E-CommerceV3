package com.donabotics.myStore1.rest;

import com.donabotics.myStore1.entity.Admin;
import com.donabotics.myStore1.services.AdminLoginAndRegistrationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminLoginAndRegisterController {
    private final AdminLoginAndRegistrationServices services;

    @Autowired
    public AdminLoginAndRegisterController(AdminLoginAndRegistrationServices services) {
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

        return "redirect:/admin/login";
    }

    @GetMapping("/admin/login")
    public String loginPage(Model model) {
        model.addAttribute("admin", new Admin());

        return "adminLogin";
    }

    @PostMapping("/admin/login")
    public String verify(Admin admin, RedirectAttributes re) {
        assert admin != null;
        if (services.verifyLogin(admin)){
            return "redirect:/admin_home";
        }
        re.addFlashAttribute("message", "Invalid Password or Email");
        re.addFlashAttribute("alertClass", "alert-danger");
         return  "adminLogin";
    }
}
