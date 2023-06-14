package com.diploma.profanity_filter.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PingController {

    @GetMapping(path = "/ping", produces = "application/json")
    public String isAliveGet(){
        return "pong";
    }
}
