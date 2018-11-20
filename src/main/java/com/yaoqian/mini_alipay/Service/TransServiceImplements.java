package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.TransactionEntity;
import com.yaoqian.mini_alipay.mapper.TransactionMapper;
import com.yaoqian.mini_alipay.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TransServiceImplements implements TransService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransactionMapper transactionMapper;

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
        transactionMapper.save(record);
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
