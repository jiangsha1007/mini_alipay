package com.yaoqian.mini_alipay.mapper;

import com.yaoqian.mini_alipay.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/5/12.
 */

@Repository
public interface UserDao extends JpaRepository<UserEntity,String>{

    UserEntity findByUsername(String username);

}