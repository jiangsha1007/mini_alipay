package com.yaoqian.mini_alipay.entity;
import java.util.Map;
public class ResultEntity {
    private int status;// 返回码
    private String message;// 返回消息
    private Map<String, Object> data;// 数据源

    public int getErrcode() {
        return status;
    }
    public void setErrcode(int errcode) {
        this.status = status;
    }
    public String getErrmsg() {
        return message;
    }
    public void setErrmsg(String errmsg) {
        this.message = errmsg;
    }
    public Map<String, Object> getData() {
        return data;
    }
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
