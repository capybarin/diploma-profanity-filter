package com.diploma.profanity_filter.controllers;

import com.diploma.profanity_filter.models.PongModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PingController {

    @GetMapping(path = "/ping", produces = "application/json")
    public PongModel isAliveGet(){
        PongModel pongModel = new PongModel();
        return pongModel;
    }

    @PostMapping(path = "/ping", produces = "application/json")
    public PongModel isAlivePost(){
        PongModel pongModel = new PongModel();
        return pongModel;
    }
}
