package com.yaoqian.mini_alipay.controller;

import com.yaoqian.mini_alipay.Service.TransService;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransController {

    @Autowired
    private TransService transService;

    /***
     * 转账
     * @param out_usrname
     * @param in_usrname
     * @param amount
     * @return ResultEntity
     * @throws Exception
     */
    @PostMapping(value = "/transfer")
    public ResultEntity UserTransfer(@RequestParam("out_usrname") String out_usrname,
                                     @RequestParam("in_username") String in_usrname,
                                     @RequestParam("amount") Float amount) throws Exception{
        return transService.Transfer(out_usrname, in_usrname, amount);
    }
}
