package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.NoticeEntity;

public interface NoticeService {

    NoticeEntity queryNotice(String uid);

    int addNotice(String uid,String to_uid,String time,String text,int type,int read);

    int updateNotice( long nid);
}
