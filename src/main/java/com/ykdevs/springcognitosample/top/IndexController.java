package com.ykdevs.springcognitosample.top;

import com.ykdevs.springcognitosample.session.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
    private SessionUser user;

    public IndexController(SessionUser user) {
        this.user = user;
    }

    @GetMapping
    public String indexPage(Model model) {
        if (user.getEmail()==null) {
            return "redirect:/login";
        }
        model.addAttribute("email", user.getEmail());
        return "index";
    }
}
