package com.ykdevs.springcognitosample.auth;

import java.net.URI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/login")
public class LoginController {

    private CognitoProperty cognitoProperty;

    public LoginController(CognitoProperty cognitoProperty) {
        this.cognitoProperty = cognitoProperty;
    }

    @GetMapping
    public String loginPage(Model model) {
        model.addAttribute("userPoolId", cognitoProperty.getUserPoolId());
        model.addAttribute("clientId", cognitoProperty.getUserPoolClientId());
        model.addAttribute("region", cognitoProperty.getRegion());
        model.addAttribute("identityPoolId", cognitoProperty.getIdPoolId());
        model.addAttribute("userPoolUrl", cognitoProperty.getUserPoolUrl());

        URI url = UriComponentsBuilder.fromUriString(cognitoProperty.getAuthorizationUri())
                .queryParam("identity_provider", "AzureAD")
                .queryParam("redirect_uri", cognitoProperty.getRedirectUri())
                .queryParam("response_type", "CODE")
                .queryParam("client_id", cognitoProperty.getUserPoolClientId())
                .queryParam("scope", "aws.cognito.signin.user.admin email openid phone profile")
                .build()
                .toUri();
        model.addAttribute("azureLoginUri", url);
        return "login";
    }
}
