package com.ykdevs.springcognitosample.controller;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aws.cognito")
public class CognitoProperty {
    private String region;
    private UserPoolProperty userPool;
    private IdPoolProperty idPool;

    public String getUserPoolId() {
        return userPool.getId();
    }

    public String getUserPoolClientId() {
        return userPool.getClientId();
    }

    public String getUserPoolUrl() {
        return String.format(userPool.getUrlFormat(), region, userPool.getId());
    }

    public String getIdPoolId() {
        return idPool.getId();
    }

    public String getTokenUri() {
        return userPool.getTokenUri();
    }

    public String getAuthorizationUri() {
        return userPool.getAuthorizationUri();
    }

    public String getRedirectUri() {
        return userPool.getRedirectUri();
    }

    @Data
    private static class UserPoolProperty {
        private String id;
        private String clientId;
        final private String urlFormat = "cognito-idp.%s.amazonaws.com/%s";
        private String tokenUri;
        private String authorizationUri;
        private String redirectUri;
    }

    @Data
    private static class IdPoolProperty {
        private String id;
    }
}

