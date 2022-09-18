package com.ykdevs.springcognitosample.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private CognitoProperty cognitoProperty;

    public SignupController(CognitoProperty cognitoProperty) {
        this.cognitoProperty = cognitoProperty;
    }

    @GetMapping
    public String signupPage(Model model) {
        model.addAttribute("userPoolId", cognitoProperty.getUserPoolId());
        model.addAttribute("clientId", cognitoProperty.getUserPoolClientId());
        return "signup";
    }
}
