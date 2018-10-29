package com.yaoqian.mini_alipay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/greeting")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public String greeting(){
        return "Hello there";
    }
}

