package com.ykdevs.springcognitosample.controller;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.net.URL;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoJwtVerifier {

    @Getter
    private String email;

    @Getter
    private String role;

    public void verify(String authorization, String userPoolUrl, String idpKeyUrl) {
        String idToken = authorization.replace("Bearer ", "");
        log.info("IdToken = " + idToken);
        try {
            DecodedJWT decodedJWT = JWT.decode(idToken);
            Jwk jwk = new UrlJwkProvider(new URL(idpKeyUrl)).get(decodedJWT.getKeyId());
            final PublicKey testKey = jwk.getPublicKey();
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) testKey, null);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(userPoolUrl).build();
            verifier.verify(idToken);

            email = decodedJWT.getClaim("email").asString();
            role = decodedJWT.getClaim("custom:role").asString();

        } catch (Exception exception) {
            //Invalid signature/claims
            throw new IllegalArgumentException();
        }
    }
}