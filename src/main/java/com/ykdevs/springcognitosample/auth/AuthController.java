package com.ykdevs.springcognitosample.auth;

import com.ykdevs.springcognitosample.session.SessionUser;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    final private SessionUser user;
    final private String userPoolUrl;
    final private String idpKeyUrl;

    public AuthController(SessionUser user, CognitoProperty cognitoProperty) {
        this.user = user;
        this.userPoolUrl = "https://" + cognitoProperty.getUserPoolUrl();
        this.idpKeyUrl = userPoolUrl + "/.well-known/jwks.json";
    }

    @GetMapping
    public Map<String, String> auth(@RequestHeader(name = "Authorization", required = false) String authorization) {

        SampleJwtVerifier sampleJwtVerifier = new SampleJwtVerifier();
        sampleJwtVerifier.verify(authorization, this.userPoolUrl, this.idpKeyUrl);
        this.user.setEmail(sampleJwtVerifier.getEmail());
        this.user.setRole(sampleJwtVerifier.getRole());

        return new HashMap<String, String>() {
            {
                put("result", "OK");
                put("email", user.getEmail());
            }
        };
    }
}
