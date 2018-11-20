package com.yaoqian.mini_alipay.mapper;

import com.yaoqian.mini_alipay.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends JpaRepository<TransactionEntity,String> {
}
