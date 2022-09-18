package com.ykdevs.springcognitosample.auth;

import lombok.Data;

@Data
public class AzureIdentity {
    private String userId;
    private String providerName;
    private String providerType;
    private String issuer;
    private String primary;
    private String dateCreated;
}
