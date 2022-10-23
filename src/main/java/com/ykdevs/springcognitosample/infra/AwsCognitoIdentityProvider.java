package com.ykdevs.springcognitosample.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

@Configuration
public class AwsCognitoIdentityProvider {
    @Bean
    public CognitoIdentityProviderClient cognitoClient() {
        return CognitoIdentityProviderClient.builder()
                .credentialsProvider(DefaultCredentialsProvider.create()) // ...... (2)
                .region(Region.AP_NORTHEAST_1)
                .build();
    }
}
