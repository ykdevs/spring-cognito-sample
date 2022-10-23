package com.ykdevs.springcognitosample.auth;

import com.ykdevs.springcognitosample.infra.CognitoIdentityProviderGateway;
import com.ykdevs.springcognitosample.session.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/update")
public class UpdateController {
    private SessionUser user;
    private CognitoIdentityProviderGateway cognitoIdentityProviderGateway;

    public UpdateController(SessionUser user,
                            CognitoIdentityProviderGateway cognitoIdentityProviderGateway) {
        this.user = user;
        this.cognitoIdentityProviderGateway = cognitoIdentityProviderGateway;
    }

    @GetMapping
    public String updatePage(Model model) {
        model.addAttribute("email", user.getEmail());
        return "update";
    }

    @PostMapping
    public String update(@ModelAttribute UserRequest userRequest) {
        this.cognitoIdentityProviderGateway.updateEmail(user.getEmail(), userRequest.getEmail());
        user.setEmail(userRequest.getEmail());
        return "index";
    }
}
