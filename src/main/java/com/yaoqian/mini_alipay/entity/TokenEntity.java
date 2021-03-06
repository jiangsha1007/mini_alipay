package com.yaoqian.mini_alipay.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="token_info")
public class TokenEntity {
    @Id
    private  String uid;
    private  String token;

    public TokenEntity() {
    }

    public TokenEntity(String uid, String token) {
        this.uid = uid;
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
