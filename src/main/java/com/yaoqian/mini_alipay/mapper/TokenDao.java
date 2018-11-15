package com.yaoqian.mini_alipay.mapper;

import com.yaoqian.mini_alipay.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenDao extends JpaRepository<TokenEntity,String> {
}
