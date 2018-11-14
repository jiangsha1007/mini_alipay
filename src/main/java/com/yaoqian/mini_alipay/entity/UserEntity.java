package com.yaoqian.mini_alipay.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
/**
 * Created by Administrator on 2017/5/12.
 */

@Entity(name="user_info")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long uid;
    @Column(length = 20)
    private String username;
    @Column(length = 20)
    private String password;

    public Long getId() {
        return uid;
    }

    public void setId(Long id) {
        this.uid = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}