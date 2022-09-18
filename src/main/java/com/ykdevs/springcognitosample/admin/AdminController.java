package com.ykdevs.springcognitosample.admin;

import com.ykdevs.springcognitosample.session.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private SessionUser user;

    public AdminController(SessionUser user) {
        this.user = user;
    }

    @GetMapping
    public String adminPage(Model model) {
        if (user.getEmail()==null) {
            return "redirect:/login";
        }
        if (!user.getRole().equals("admin")) {
            return "redirect:/403.html";
        }
        model.addAttribute("email", user.getEmail());
        return "admin";
    }
}
