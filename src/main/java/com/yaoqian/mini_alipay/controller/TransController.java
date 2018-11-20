package com.yaoqian.mini_alipay.controller;

import com.yaoqian.mini_alipay.Service.TransService;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;
import com.yaoqian.mini_alipay.mapper.UserDao;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class TransController {

    @Autowired
    private TransService transService;

    @Autowired
    private UserDao userDao;

    /***
     * 转账
     * @param out_usrname
     * @param in_usrname
     * @param amount
     * @return ResultEntity
     * @throws Exception
     */
    @Transactional
    @PostMapping(value = "/transfer")
    public ResultEntity UserTransfer(@RequestParam("out_usrname") String out_usrname,
                                     @RequestParam("in_username") String in_usrname,
                                     @RequestParam("amount") Float amount) throws Exception{
        UserEntity out_user = userDao.findByUsername(out_usrname);
        UserEntity in_user = userDao.findByUsername(in_usrname);
        if(out_user!=null && in_user!=null) {
            if (out_user.getBalance() >= amount) {
                out_user.setBalance(out_user.getBalance() - amount);
                in_user.setBalance(in_user.getBalance() + amount);
                userDao.save(out_user);
                userDao.save(in_user);
                //save transaction records
                transService.TwoSuccessTransferRecord(out_user.getUid(), in_user.getUid(), amount);
                return ResultTools.success("转账成功");
            }
            else{
                transService.TwoFailTransferRecord(out_user.getUid(), in_user.getUid(), amount, "余额不足");
                throw new Exception("余额不足");
            }
        }
        else {
            transService.TwoFailTransferRecord(out_user.getUid(), in_user.getUid(), amount, "用户名不存在");
            throw new Exception("用户名不存在！");
        }
    }
}