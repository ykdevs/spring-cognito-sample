package com.ykdevs.springcognitosample.auth;

import com.ykdevs.springcognitosample.session.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/callback")
public class CallbackController {
    final private SessionUser user;
    final private CognitoProperty cognitoProperty;
    final private OpenIdTokenService openIdTokenService;
    final private String userPoolUrl;
    final private String idpKeyUrl;

    public CallbackController(SessionUser user, CognitoProperty cognitoProperty,
            OpenIdTokenService openIdTokenService) {
        this.user = user;
        this.cognitoProperty = cognitoProperty;
        this.openIdTokenService = openIdTokenService;

        this.userPoolUrl = "https://" + cognitoProperty.getUserPoolUrl();
        this.idpKeyUrl = userPoolUrl + "/.well-known/jwks.json";
    }

    @GetMapping()
    public String callback(@RequestParam(name = "code", required = true) String authenticationCode) {
        String idToken = this.openIdTokenService.getIdToken(this.cognitoProperty.getTokenUri(),
                this.cognitoProperty.getUserPoolClientId(), authenticationCode,
                this.cognitoProperty.getRedirectUri());

        SampleJwtVerifier sampleJwtVerifier = new SampleJwtVerifier();
        sampleJwtVerifier.verify(idToken, this.userPoolUrl, this.idpKeyUrl);
        this.user.setEmail(sampleJwtVerifier.getEmail());
        this.user.setRole(sampleJwtVerifier.getRole());

        return "redirect:/";
    }
}
