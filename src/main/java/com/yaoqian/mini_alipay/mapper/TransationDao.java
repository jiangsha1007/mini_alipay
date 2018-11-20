package com.yaoqian.mini_alipay.mapper;

import com.yaoqian.mini_alipay.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransationDao extends JpaRepository<TransactionEntity,String> {
}
