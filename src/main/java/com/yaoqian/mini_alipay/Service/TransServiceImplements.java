package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.TransactionEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;
import com.yaoqian.mini_alipay.mapper.TransactionDao;
import com.yaoqian.mini_alipay.mapper.UserDao;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TransServiceImplements implements TransService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransactionDao transactionDao;

    @Transactional  //事件，用于同时修改两个账户
    @Override
    public ResultEntity Transfer(String out_usrname, String in_usrname, Float amount) throws Exception{
        UserEntity out_user = userDao.findByUsername(out_usrname);
        UserEntity in_user = userDao.findByUsername(in_usrname);
        if(out_user!=null && in_user!=null) {
            if (out_user.getBalance() >= amount) {
                out_user.setBalance(out_user.getBalance() - amount);
                in_user.setBalance(in_user.getBalance() + amount);
                userDao.save(out_user);
                userDao.save(in_user);
                //save transaction records
                TwoSuccessTransferRecord(out_user.getUid(), in_user.getUid(), amount);
                return ResultTools.success("转账成功");
            }
            else{
                TwoFailTransferRecord(out_user.getUid(), in_user.getUid(), amount, "余额不足");
                throw new Exception("余额不足");
            }
        }
        else {
            TwoFailTransferRecord(out_user.getUid(), in_user.getUid(), amount, "用户不存在");
            throw new Exception("用户名不存在！");
        }
    }

    @Override
    public void CreateRecord(String transUid, String transObjUid, Integer transType, Float amount, Integer transStatus, Integer transCategoryId, String transRemarks){
        TransactionEntity record = new TransactionEntity();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
//	    System.out.println("格式化输出：" + sdf.format(d));
        String a = sdf.format(d);
        System.out.println(a.substring(0,4));
        record.setTrans_year(Integer.valueOf(a.substring(0,4)));
        record.setTrans_month(Integer.valueOf(a.substring(5,7)));
        record.setTrans_day(Integer.valueOf(a.substring(8,10)));
        record.setTrans_time(a.substring(11));
        record.setTrans_type(transType);
        record.setTrans_category_id(transCategoryId);
        record.setTrans_cost(amount);
        record.setTrans_uid(transUid);
        record.setTrans_obj_id(transObjUid);
        record.setTrans_status(transStatus);
        record.setTrans_remarks(transRemarks);
        transactionDao.save(record);
    }

    @Override
    public void SuccesRecord(String transUid, String transObjUid, Integer transType, Float amount, Integer transCategoryId){
        CreateRecord(transUid, transObjUid, transType, amount, 0, transCategoryId, "成功");
    }

    @Override
    public void FailRecord(String transUid, String transObjUid, Integer transType, Float amount, Integer transCategoryId, String transRemarks){
        CreateRecord(transUid, transObjUid, transType, amount, 1, transCategoryId, transRemarks);
    }

    @Override
    public void TwoSuccessTransferRecord(String transOutUid, String transInUid, Float amount){
        SuccesRecord(transOutUid, transInUid, 0, amount, 1);
        SuccesRecord(transInUid, transOutUid, 1, amount, 1);
    }

    @Override
    public void TwoFailTransferRecord(String transOutUid, String transInUid, Float amount, String transRemarks){
        FailRecord(transOutUid, transInUid, 0, amount, 1, transRemarks);
        FailRecord(transInUid, transOutUid, 1, amount, 1, transRemarks);
    }
}
