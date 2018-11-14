package com.yaoqian.mini_alipay.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity(name="Transaction_info")
public class TransactionEntity {
    @Id
    @GeneratedValue
    private Long Trans_id;
    @Column(length = 20)
    private String Trans_no;
    @Column(length = 11)
    private Integer Trans_year;
    @Column(length = 11)
    private Integer Trans_month;
    @Column(length = 11)
    private Integer Trans_day;
    @Column(length = 20)
    private String Trans_time;
    @Column(length = 4)
    private Integer Trans_type;
    @Column(length = 11)
    private Float Trans_cost;
    @Column(length = 11)
    private Integer Trans_category_id;
    @Column(length = 20)
    private String Trans_uid;
    @Column(length = 20)
    private String Trans_obj_id;
    @Column(length = 500)
    private String Trans_remarks;
    @Column(length = 4)
    private Integer Trans_status;

    public String getTrans_no() {
        return Trans_no;
    }
    public void setTrans_no(String Trans_no) {
        this.Trans_no = Trans_no;
    }

    public int getTrans_year() {
        return Trans_year;
    }
    public void setTrans_year(int Trans_year) {
        this.Trans_year = Trans_year;
    }

    public int getTrans_month() {
        return Trans_month;
    }
    public void setTrans_month(int Trans_month) {
        this.Trans_month = Trans_month;
    }

    public int getTrans_day() {
        return Trans_day;
    }
    public void setTrans_day(int Trans_day) {
        this.Trans_day = Trans_day;
    }

    public String getTrans_time() {
        return Trans_time;
    }
    public void setTrans_time(String Trans_time) {
        this.Trans_time = Trans_time;
    }

    public int getTrans_type() {
        return Trans_type;
    }
    public void setTrans_type(int Trans_type) {
        this.Trans_type = Trans_type;
    }

    public float getTrans_cost() {
        return Trans_cost;
    }
    public void setTrans_cost(float Trans_cost) {
        this.Trans_cost = Trans_cost;
    }

    public int getTrans_category_id() {
        return Trans_category_id;
    }
    public void setTrans_category_id(int Trans_category_id) {
        this.Trans_category_id = Trans_category_id;
    }

    public String getTrans_uid() {
        return Trans_uid;
    }
    public void setTrans_uid(String Trans_uid) {
        this.Trans_uid = Trans_uid;
    }

    public String getTrans_obj_id() {
        return Trans_obj_id;
    }
    public void setTrans_obj_id(String Trans_obj_id) {
        this.Trans_obj_id = Trans_obj_id;
    }

    public String getTrans_remarks() {
        return Trans_remarks;
    }
    public void setTrans_remarks(String Trans_remarks) {
        this.Trans_remarks = Trans_remarks;
    }

    public int getTrans_status() {
        return Trans_status;
    }
    public void setTrans_status(int Trans_status) {
        this.Trans_status = Trans_status;
    }
}
