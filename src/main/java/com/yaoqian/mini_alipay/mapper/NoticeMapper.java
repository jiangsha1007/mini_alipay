package com.yaoqian.mini_alipay.mapper;

import com.yaoqian.mini_alipay.entity.NoticeEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NoticeMapper {
    /*******查询最新未接受通知数据********/
    @Select("select * from notice where notice_to_uid = #{arg0} and notice_read=1 order by notice_id Desc")
    NoticeEntity queryNotice(String uid);

    /*******增加通知********/
    @Insert("insert into  notice (notice_uid,notice_to_uid,notice_time,notice_text,notice_type,notice_read)values (#{arg0},#{arg0},#{arg0},#{arg0},#{arg0},#{arg0})")
    int addNotice(String uid,String to_uid,String time,String text,int type,int read);

    /*******修改通知********/
    @Update("update notice set notice_read=0 where notice_id= #{arg0}")
    int updateNotice( long nid);
}
