package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;
import com.yaoqian.mini_alipay.mapper.UserDao;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserServiceImplements implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResultEntity Register(UserEntity userEntity){
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

    @Override
    public  ResultEntity PersonalInfo(UserEntity currentUser){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userEntity",currentUser);
        return ResultTools.success(map);
    }

    @Override
    public ResultEntity UpdateInfo(UserEntity newUserInfo,UserEntity currentUser){
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

    @Override
    public ResultEntity Delete(UserEntity currentUser){
        tokenService.deleteToken(currentUser.getUid());
        userDao.delete(currentUser.getUid());
        return ResultTools.success("注销成功");
    }

    @Override
    public UserEntity save(UserEntity userEntity){
        return userDao.save(userEntity);
    }

    @Override
    public UserEntity findOne(String id){
        return userDao.findOne(id);
    }

    @Override
    public UserEntity findByUsername(String username){
        return userDao.findByUsername(username);
    }
}
