package com.yaoqian.mini_alipay.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="notice")
public class NoticeEntity {
    @Id
    @GeneratedValue
    private long Notice_id;// 消息id
    @Column(length = 11)
    private int Notice_type;// 消息类型
    @Column(length = 50)
    private String Notice_time;// 消息时间
    @Column(length = 2000)
    private String Notice_text;// 消息内容
    @Column(length = 50)
    private String Notice_to_uid;// 消息接受者
    @Column(length = 50)
    private String Notice_uid;// 消息发送者
    @Column(length = 1)
    private int Notice_read;// 消息是否阅读

    public long getNotice_id() {
        return Notice_id;
    }
    public void setNotice_id(long Notice_id) {
        this.Notice_id = Notice_id;
    }
    public int getNotice_type() {
        return Notice_type;
    }
    public void setNotice_type(int Notice_type) {
        this.Notice_type = Notice_type;
    }

    public String getNotice_text() {
        return Notice_text;
    }
    public void setNotice_text(String Notice_text) {
        this.Notice_text = Notice_text;
    }

    public String getNotice_time() {
        return Notice_time;
    }
    public void setNotice_time(String Notice_time) {
        this.Notice_time = Notice_time;
    }
    public String getNotice_to_uid() {
        return Notice_to_uid;
    }
    public void setNotice_to_uid(String Notice_to_uid) {
        this.Notice_to_uid = Notice_to_uid;
    }
    public String geNotice_uid() {
        return Notice_uid;
    }
    public void setNotice_uid(String Notice_uid) {
        this.Notice_uid = Notice_uid;
    }
    public int getNotice_read() {
        return Notice_read;
    }
    public void setNotice_read(int Notice_read) {
        this.Notice_read = Notice_read;
    }

}
