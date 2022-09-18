package com.ykdevs.springcognitosample.auth;

import lombok.Data;

@Data
public class OpenIdToken {
    private String id_token;
    private String access_token;
    private String refresh_token;
    private Integer expires_in;
    private String token_type;

    public String getIdToken() {
        return this.id_token;
    }
}
