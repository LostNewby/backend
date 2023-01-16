package com.naturalgoods.backend.welcome;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class Welcome {

    @GetMapping
    public String defultPage() {
        return "Natural Goods webserver is successfully deployed! This is good";
    }
}
