package com.yaoqian.mini_alipay.controller;

import com.github.pagehelper.PageHelper;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.TransactionEntity;
import com.yaoqian.mini_alipay.mapper.TransactionMapper;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/bill")
public class billController {
    @Autowired
    private TransactionMapper TransactionMapper;
    @RequestMapping(value = { "/QueryTransListByTime" }, method = RequestMethod.POST)
    public ResultEntity QueryTransListByTime(String uid, Integer page, Integer pageSize,Integer time_type,String time_start,String time_end,Integer trans_category,Integer trans_type) {
        try {
            if (null == uid) {
                return ResultTools.result(1001, "uid is empty", null);
            }
            PageHelper.startPage(page, pageSize);// 分页配置
            String trans_type_str;
            if(trans_type== null)
                trans_type_str = "(0,1)";
            else
                trans_type_str = "("+trans_type+")";
            List<TransactionEntity> Translist;
            /*1 按月 2 按日 */
            if(time_type==1){
                /*按照xxxx-xx格式* 个位不加0*/
                int year= Integer.parseInt(time_start.split("-")[0]);
                int month= Integer.parseInt(time_start.split("-")[1]);
                Translist = TransactionMapper.QueryTranslistByTime_y(uid,year,month,trans_type_str);
            }
            else if(time_type==2){
                /*按照xxxx-xx-xx格式 个位加0*/
                Translist = TransactionMapper.QueryTranslistByTime_d(uid,time_start,time_end,trans_type_str);
            }
            else {
                Translist = TransactionMapper.QueryTranslistByTime(uid);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("count", Translist.size());
            map.put("page", page);
            map.put("content", Translist);
            return ResultTools.result(0, "",map);
        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }
}
