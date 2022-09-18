package com.ykdevs.springcognitosample.session;

import java.util.Objects;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Component
@SessionScope
public class SessionUser {
    private String email;
    private String role;

    public SessionUser() {
        this.email = null;
        this.role = "user";
    }

    public void setRole(String role) {
        if (Objects.isNull(role)) {
            this.role = "user";
        } else {
            this.role = "admin";
        }
    }
}