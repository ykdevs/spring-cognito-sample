package com.ykdevs.springcognitosample.auth;

import com.ykdevs.springcognitosample.infra.CognitoIdentityProviderGateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup2")
public class Signup2Controller {
    private CognitoIdentityProviderGateway cognitoIdentityProviderGateway;

    public Signup2Controller(CognitoIdentityProviderGateway cognitoIdentityProviderGateway) {
        this.cognitoIdentityProviderGateway = cognitoIdentityProviderGateway;
    }

    @GetMapping
    public String signupPage(Model model) {
        return "signup2";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute UserRequest userRequest) {
        this.cognitoIdentityProviderGateway.createUser(userRequest.getEmail(), userRequest.getEmail());
        this.cognitoIdentityProviderGateway.setUsrPassword(userRequest.getEmail(), userRequest.getPassword(), true);
        this.cognitoIdentityProviderGateway.addUserToGroup(userRequest.getEmail(), "TEST_COMPANY");
        return "login";
    }
}
