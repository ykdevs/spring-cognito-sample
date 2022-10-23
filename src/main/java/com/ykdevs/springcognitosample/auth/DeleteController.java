package com.ykdevs.springcognitosample.auth;

import com.ykdevs.springcognitosample.infra.CognitoIdentityProviderGateway;
import com.ykdevs.springcognitosample.session.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete")
public class DeleteController {
    private SessionUser user;
    private CognitoIdentityProviderGateway cognitoIdentityProviderGateway;

    public DeleteController(SessionUser user,
                            CognitoIdentityProviderGateway cognitoIdentityProviderGateway) {
        this.user = user;
        this.cognitoIdentityProviderGateway = cognitoIdentityProviderGateway;
    }

    @GetMapping
    public String delete() {
        this.cognitoIdentityProviderGateway.deleteUser(this.user.getEmail());
        this.user.setEmail(null);
        return "signup";
    }
}
