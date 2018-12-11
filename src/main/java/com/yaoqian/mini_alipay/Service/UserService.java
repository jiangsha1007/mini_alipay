package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;

public interface UserService {

    ResultEntity Register(UserEntity userEntity);

    ResultEntity PersonalInfo(UserEntity currentUser);

    ResultEntity UpdateInfo(UserEntity newUserInfo,UserEntity currentUser);

    ResultEntity Delete(UserEntity currentUser);

    UserEntity save(UserEntity userEntity);

    UserEntity findOne(String id);

    UserEntity findByUsername(String username);
}
