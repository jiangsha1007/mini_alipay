package com.yaoqian.mini_alipay.controller;
import com.yaoqian.mini_alipay.mapper.UserDao;
import com.yaoqian.mini_alipay.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @PostMapping(value = "/login")
    public String userlogin(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "pwd", required = false) String pwd)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(name);
        userEntity.setPassword(pwd);
        userDao.save(userEntity);
        return "index";
    }
}
