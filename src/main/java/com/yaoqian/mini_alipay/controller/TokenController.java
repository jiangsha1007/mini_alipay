package com.yaoqian.mini_alipay.controller;

import com.yaoqian.mini_alipay.Service.TokenService;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.TokenEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;
import com.yaoqian.mini_alipay.mapper.UserDao;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResultEntity login(@RequestParam String userName,
                              @RequestParam String password) throws Exception {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            throw new Exception("用户名或密码为空");
        }
        UserEntity user = userDao.findByUsername(userName);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        if (password.equals(user.getPassword())) {
            TokenEntity tokenEntity = tokenService.createToken(user.getUid());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tokenEntity",tokenEntity);
            return ResultTools.result(0,"",map);
        }
        throw new Exception("用户名或密码错误");
    }


}
