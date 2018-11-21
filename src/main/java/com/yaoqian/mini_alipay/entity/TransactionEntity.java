package com.yaoqian.mini_alipay.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity(name="Transaction_info")
public class TransactionEntity {

    /***
     * 交易id
     */
    @Id
    @GeneratedValue
    private Long Trans_id;

    /***
     * 交易单号
     */
    private String Trans_no;

    /***
     * 交易年份
     */
    private Integer Trans_year;

    /***
     * 交易月份
     */
    private Integer Trans_month;

    /***
     * 交易日期
     */
    private Integer Trans_day;

    /***
     * 交易时间
     */
    private String Trans_time;

    /***
     * 交易类型
     * 0：支出 1：收入
     */
    private Integer Trans_type;

    /***
     * 交易金额
     */
    private Float Trans_cost;

    /***
     * 交易类别
     * 1： 转账 2：付款 3：缴费 4：还款 5：退款: 6：网购
     */
    private Integer Trans_category_id;

    /***
     * 交易主体用户id
     */
    @Column(length = 32)
    private String Trans_uid;

    /***
     * 交易主体用户名
     */
    private String Trans_name;

    /***
     * 交易目标用户id
     */
    @Column(length = 32)
    private String Trans_obj_id;

    /***
     * 交易目标用户名
     */
    @Column(length = 32)
    private String Trans_obj_name;

    /***
     * 交易标注
     */
    private String Trans_remarks;

    /***
     * 交易状态
     * 0 ：已完成 1：失败
     */
    private Integer Trans_status;

    public Long getTrans_id() {
        return Trans_id;
    }

    public void setTrans_id(Long trans_id) {
        Trans_id = trans_id;
    }

    public String getTrans_no() {
        return Trans_no;
    }

    public void setTrans_no(String trans_no) {
        Trans_no = trans_no;
    }

    public Integer getTrans_year() {
        return Trans_year;
    }

    public void setTrans_year(Integer trans_year) {
        Trans_year = trans_year;
    }

    public Integer getTrans_month() {
        return Trans_month;
    }

    public void setTrans_month(Integer trans_month) {
        Trans_month = trans_month;
    }

    public Integer getTrans_day() {
        return Trans_day;
    }

    public void setTrans_day(Integer trans_day) {
        Trans_day = trans_day;
    }

    public String getTrans_time() {
        return Trans_time;
    }

    public void setTrans_time(String trans_time) {
        Trans_time = trans_time;
    }

    public Integer getTrans_type() {
        return Trans_type;
    }

    public void setTrans_type(Integer trans_type) {
        Trans_type = trans_type;
    }

    public Float getTrans_cost() {
        return Trans_cost;
    }

    public void setTrans_cost(Float trans_cost) {
        Trans_cost = trans_cost;
    }

    public Integer getTrans_category_id() {
        return Trans_category_id;
    }

    public void setTrans_category_id(Integer trans_category_id) {
        Trans_category_id = trans_category_id;
    }

    public String getTrans_uid() {
        return Trans_uid;
    }

    public void setTrans_uid(String trans_uid) {
        Trans_uid = trans_uid;
    }

    public String getTrans_obj_id() {
        return Trans_obj_id;
    }

    public void setTrans_obj_id(String trans_obj_id) {
        Trans_obj_id = trans_obj_id;
    }

    public String getTrans_remarks() {
        return Trans_remarks;
    }

    public void setTrans_remarks(String trans_remarks) {
        Trans_remarks = trans_remarks;
    }

    public Integer getTrans_status() {
        return Trans_status;
    }

    public void setTrans_status(Integer trans_status) {
        Trans_status = trans_status;
    }

    public String getTrans_name() {
        return Trans_name;
    }

    public void setTrans_name(String trans_name) {
        Trans_name = trans_name;
    }

    public String getTrans_obj_name() {
        return Trans_obj_name;
    }

    public void setTrans_obj_name(String trans_obj_name) {
        Trans_obj_name = trans_obj_name;
    }
}
