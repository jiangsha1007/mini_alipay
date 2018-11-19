package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.TransactionEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;
import com.yaoqian.mini_alipay.mapper.TransactionMapper;
import com.yaoqian.mini_alipay.mapper.UserDao;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TransServer {

    @Autowired
    private UserDao userDao;
    private TransactionMapper transactionMapper;

    @Transactional  //事件，用于同时修改两个账户
    public ResultEntity Transfer(String out_id, String in_id, float amount) throws Exception{
        UserEntity out_user = userDao.findOne(out_id);
        UserEntity in_user = userDao.findOne(in_id);
        if(out_user!=null && in_user!=null) {
            if (out_user.getBalance() > amount) {
                out_user.setBalance(out_user.getBalance() - amount);
                in_user.setBalance(in_user.getBalance() + amount);
                userDao.save(out_user);
                userDao.save(in_user);
                //save transaction record
                CreateRecord(out_id, in_id, amount);

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("UserEntity",in_user);
                return ResultTools.result(0,"交易成功",map);
            }
            else{
                throw new Exception("余额不足");
            }
        }
        else {
            throw new Exception("用户名不存在！");
        }
    }

    public void CreateRecord(String out_id, String in_id, float amount){
        TransactionEntity record = new TransactionEntity();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
//	    System.out.println("格式化输出：" + sdf.format(d));
        String a = sdf.format(d);
        System.out.println(a.substring(0,4));
        Integer year = Integer.valueOf(a.substring(0,4));
        Integer month = Integer.valueOf(a.substring(5,7));
        Integer day = Integer.valueOf(a.substring(8,10));
        String time = a.substring(11);
        record.setTrans_year(year);
        record.setTrans_month(month);
        record.setTrans_day(day);
        record.setTrans_cost(amount);
        record.setTrans_obj_id(in_id);
        record.setTrans_status(0);
        record.setTrans_type(1);
        record.setTrans_uid(out_id);
        //如何保存？
        transactionMapper.addUser(record);
    }
}

