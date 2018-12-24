package com.yaoqian.mini_alipay.mapper;

import com.yaoqian.mini_alipay.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeDao extends JpaRepository<NoticeEntity,String> {
}
