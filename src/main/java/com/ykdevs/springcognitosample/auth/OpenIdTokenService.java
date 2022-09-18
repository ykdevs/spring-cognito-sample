package com.ykdevs.springcognitosample.auth;

import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenIdTokenService {
    private final RestTemplate restTemplate;

    public OpenIdTokenService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public OpenIdToken getOpenIdToken(String tokenUrl, String clientId, String authenticationCode, String redirectUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
        map.add("code", authenticationCode);
        map.add("redirect_uri", redirectUrl);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<OpenIdToken> response = restTemplate.postForEntity( tokenUrl, request , OpenIdToken.class );
        HttpStatus status = response.getStatusCode();
        return response.getBody();
    }

    public String getOpenIdTokenString(String tokenUrl, String clientId, String authenticationCode, String redirectUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", clientId);
        map.add("code", authenticationCode);
        map.add("redirect_uri", redirectUrl);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( tokenUrl, request , String.class );
        HttpStatus status = response.getStatusCode();
        return response.getBody();
    }

    public String getIdToken(String tokenUrl, String clientId, String authenticationCode, String redirectUrl) {
        return getOpenIdToken(tokenUrl, clientId, authenticationCode, redirectUrl).getIdToken();
        //return getOpenIdTokenString(tokenUrl, clientId, authenticationCode, redirectUrl);
    }
}
