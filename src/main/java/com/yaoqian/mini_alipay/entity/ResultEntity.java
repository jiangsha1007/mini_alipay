package com.yaoqian.mini_alipay.entity;
import java.util.Map;
public class ResultEntity {
    private int Status;// 返回码
    private String Message;// 返回消息
    private Map<String, Object> data;// 数据源

    public int getStatus() {
        return Status;
    }
    public void setStatus(int Status) {
        this.Status = Status;
    }
    public String getMessage() {
        return Message;
    }
    public void setMessage(String Message) {
        this.Message = Message;
    }
    public Map<String, Object> getData() {
        return data;
    }
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
