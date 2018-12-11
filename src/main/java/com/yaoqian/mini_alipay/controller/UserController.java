package com.yaoqian.mini_alipay.controller;

import com.yaoqian.mini_alipay.Service.UserService;
import com.yaoqian.mini_alipay.annotation.Authorization;
import com.yaoqian.mini_alipay.annotation.CurrentUser;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

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

        return userService.Register(userEntity);
    }

    /***
     * 查询个人信息
     * @param currentUser---当前用户实体类
     * @return ResultEntity---返回类
     */
    @Authorization
    @GetMapping
    public ResultEntity userPersonalInfo(@CurrentUser UserEntity currentUser){
        return userService.PersonalInfo(currentUser);
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

        return userService.UpdateInfo(newUserInfo,currentUser);
    }

    /***
     * 注销用户
     * @param currentUser---当前用户实体类
     * @return ResultEntity---返回类
     */
    @Authorization
    @DeleteMapping
    public ResultEntity userDelete(@CurrentUser UserEntity currentUser) {
        return userService.Delete(currentUser);
    }
}
