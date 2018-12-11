package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.NoticeEntity;
import com.yaoqian.mini_alipay.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoticeServiceImplements implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public NoticeEntity queryNotice(String uid){
        return noticeMapper.queryNotice(uid);
    }

    @Override
    public int addNotice(String uid,String to_uid,String time,String text,int type,int read){
        return noticeMapper.addNotice(uid, to_uid, time, text, type, read);
    }

    @Override
    public int updateNotice( long nid){
        return noticeMapper.updateNotice(nid);
    }
}
