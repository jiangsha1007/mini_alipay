package com.yaoqian.mini_alipay.controller;

import com.yaoqian.mini_alipay.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/balance/{id}")
    public float balanceFind(@PathVariable("id") String id) {
        return userService.findOne(id).getBalance();
    }

}
