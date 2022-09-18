package com.ykdevs.springcognitosample.auth;

import com.ykdevs.springcognitosample.session.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    private SessionUser user;

    public LogoutController(SessionUser user) {
        this.user = user;
    }

    @GetMapping
    public String loginPage() {
        user.setEmail(null);
        return "redirect:/login";
    }
}
