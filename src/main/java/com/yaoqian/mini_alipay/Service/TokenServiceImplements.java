package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.TokenEntity;
import com.yaoqian.mini_alipay.mapper.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Component
public class TokenServiceImplements implements TokenService {

    @Autowired
    private TokenDao tokenDao;

    @Override
    public TokenEntity createToken(String userId) {
        String token = UUID.randomUUID().toString();
        TokenEntity tokenEntity = new TokenEntity(userId, token);
        tokenDao.save(tokenEntity);
        return tokenEntity;
    }

    @Override
    public boolean checkToken(TokenEntity entity) {
        if (entity == null) {
            return false;
        }
        TokenEntity token = tokenDao.findOne(entity.getUid());
        if (token == null || StringUtils.isEmpty(token.getToken())) {
            return false;
        }
        return token.getToken().equals(entity.getToken());
    }

    @Override
    public TokenEntity getToken(String authentication) {
        //      userId 为32位字符串
        //      userId拼接token得到authentication
        //      所以要求authentication长度大于32
        if (!StringUtils.isEmpty(authentication) && authentication.length() > 32) {
            TokenEntity tokenEntity = new TokenEntity();
            String userId = authentication.substring(0, 32);
            String token = authentication.substring(32);
            tokenEntity.setUid(userId);
            tokenEntity.setToken(token);
            return tokenEntity;
        }
        return null;
    }

    @Override
    public void deleteToken(String userId) {
        tokenDao.delete(userId);
    }

}
