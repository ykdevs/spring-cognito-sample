package com.ykdevs.springcognitosample.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/confirm")
public class ConfirmController {
    private CognitoProperty cognitoProperty;

    public ConfirmController(CognitoProperty cognitoProperty) {
        this.cognitoProperty = cognitoProperty;
    }

    @GetMapping
    public String confirmPage(Model model) {
        model.addAttribute("userPoolId", cognitoProperty.getUserPoolId());
        model.addAttribute("clientId", cognitoProperty.getUserPoolClientId());
        return "confirm";
    }
}
