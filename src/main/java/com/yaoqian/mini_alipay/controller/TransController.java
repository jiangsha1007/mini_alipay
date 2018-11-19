package com.yaoqian.mini_alipay.controller;

import com.yaoqian.mini_alipay.Service.TransServer;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransServer transServer;

    @PutMapping (value = "/transfer")
    public ResultEntity UserTransfer(@RequestParam("out_id") String out_id,
                                     @RequestParam("in_id") String in_id,
                                     @RequestParam("amount") float amount) throws Exception{
        return transServer.Transfer(out_id, in_id, amount);
    }
}
