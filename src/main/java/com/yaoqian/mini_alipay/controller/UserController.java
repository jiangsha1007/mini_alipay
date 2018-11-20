package com.yaoqian.mini_alipay.controller;

import com.yaoqian.mini_alipay.Service.TokenService;
import com.yaoqian.mini_alipay.annotation.Authorization;
import com.yaoqian.mini_alipay.annotation.CurrentUser;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;
import com.yaoqian.mini_alipay.mapper.UserDao;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenService tokenService;

    /***
     * 新用户注册
     * @param userEntity---用户实体类
     * @param bindingResult---用户实体类信息检查结果
     * @return ResultEntity---返回类
     */
    @PostMapping(value = "/register")
    public ResultEntity userRegister(@Valid @RequestBody UserEntity userEntity, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResultTools.error(404,bindingResult.getFieldError().getDefaultMessage());
        }

        //判断用户名是否已存在
        if(userDao.findByUsername(userEntity.getUsername()) != null) {
            return ResultTools.error(404,"用户名已存在");
        }
        userEntity.setBalance((float)10000);
        Date date = new Date();
        userEntity.setCreateDate(date);
        userDao.save(userEntity);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userEntity",userEntity);
        return ResultTools.success("注册成功",map);
    }

    /***
     * 查询个人信息
     * @param currentUser---当前用户实体类
     * @return ResultEntity---返回类
     */
    @Authorization
    @GetMapping
    public ResultEntity userPersonalInfo(@CurrentUser UserEntity currentUser){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userEntity",currentUser);
        return ResultTools.success(map);
    }

    /***
     * 更新用户信息
     * @param newUserInfo---新的用户信息实体类
     * @param bindingResult---新的用户信息检查结果
     * @param currentUser---当前用户实体类
     * @return ResultEntity---返回类
     */
    @Authorization
    @PostMapping(value = "/update")
    public ResultEntity userUpdateInfo(@Valid UserEntity newUserInfo, BindingResult bindingResult, @CurrentUser UserEntity currentUser){
        if(bindingResult.hasErrors()){
            return ResultTools.error(404,bindingResult.getFieldError().getDefaultMessage());
        }

        //如果修改了用户名，判断新用户名是否已存在
        if(!newUserInfo.getUsername().equals(currentUser.getUsername()) && userDao.findByUsername(newUserInfo.getUsername()) != null) {
            return ResultTools.error(404,"用户名已存在");
        }
        //如果修改了密码，则删除token
        if(!newUserInfo.getPassword().equals(currentUser.getPassword())){
            tokenService.deleteToken(currentUser.getUid());
        }
        userDao.save(newUserInfo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userEntity",newUserInfo);
        return ResultTools.success(map);
    }

    /***
     * 注销用户
     * @param currentUser---当前用户实体类
     * @return ResultEntity---返回类
     */
    @Authorization
    @DeleteMapping
    public ResultEntity userDelete(@CurrentUser UserEntity currentUser) {
        tokenService.deleteToken(currentUser.getUid());
        userDao.delete(currentUser.getUid());
        return ResultTools.success("注销成功");
    }
}
