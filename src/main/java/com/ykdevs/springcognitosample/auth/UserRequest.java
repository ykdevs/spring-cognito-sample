package com.ykdevs.springcognitosample.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
    private String email;
    private String password;
}
