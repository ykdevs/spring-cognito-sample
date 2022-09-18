package com.ykdevs.springcognitosample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Controller
public class loginController {
    final private String userPoolUrl;
    final private String idpKeyUrl;

    public loginController(CognitoProperty cognitoProperty) {
        this.userPoolUrl = "https://" + cognitoProperty.getUserPoolUrl();
        this.idpKeyUrl = userPoolUrl + "/.well-known/jwks.json";
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelview,
                              HttpServletRequest request,
                              @RequestHeader(name = "Authorization", required = false) String authorization) {

        DemoJwtVerifier demoJwtVerifier = new DemoJwtVerifier();
        demoJwtVerifier.verify(authorization, this.userPoolUrl, this.idpKeyUrl);
        String user = demoJwtVerifier.getEmail();

        Enumeration<String> headerNames = request.getHeaderNames();

        String buff = "";
        while (headerNames.hasMoreElements()) {

            // ヘッダ名と値を取得
            String headerName = (String) headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            buff += headerName + " : " + headerValue;
        }

        modelview.setViewName("index");
        modelview.addObject("email", user);
        modelview.addObject("token", authorization);
        modelview.addObject("header", buff);
        return modelview;
    }
}
