package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.constant.RequestUri;

/**
 * Ping Controller for test Apis
 * 
 * @author Hema Preethi
 *
 */
@RestController
@RequestMapping(RequestUri.PING)
public class PingController {

    @GetMapping
    public String ping() {
        return "pong";
    }
    
}
