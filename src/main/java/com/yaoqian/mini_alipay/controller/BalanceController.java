package com.yaoqian.mini_alipay.controller;
import com.yaoqian.mini_alipay.mapper.TransactionMapper;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.mapper.UserDao;
import com.yaoqian.mini_alipay.tools.ResultTools;
import com.yaoqian.mini_alipay.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BalanceController {

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/balance/{id}")
    public float balanceFind(@PathVariable("id") String id) {
        return userDao.findOne(id).getBalance();
    }

}
