package com.ebay.jimo.tacocloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String login(){
        return "login";
    }
    // POST Method will be defined in the Web Security HTTP Configuration.
}
