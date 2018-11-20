package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.TransactionEntity;
import com.yaoqian.mini_alipay.mapper.TransationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class TransServiceImplements implements TransService {

    @Autowired
    private TransationDao transationDao;


    @Override
    public void CreateRecord(String transUid, String transObjUid, Integer transType, Float amount, Integer transStatus, Integer transCategoryId, String transRemarks){
        TransactionEntity record = new TransactionEntity();
        Date d = new Date();
        long timestamp = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
//	    System.out.println("格式化输出：" + sdf.format(d));
        String a = sdf.format(d);
//      System.out.println(a.substring(0,4));
        record.setTrans_year(Integer.valueOf(a.substring(0,4)));
        record.setTrans_month(Integer.valueOf(a.substring(5,7)));
        record.setTrans_day(Integer.valueOf(a.substring(8,10)));
        record.setTrans_time(a.substring(11));
        record.setTrans_type(transType);
        record.setTrans_category_id(transCategoryId);
        record.setTrans_cost(amount);
        record.setTrans_no(timestamp + getRandomNo());
        record.setTrans_uid(transUid);
        record.setTrans_obj_id(transObjUid);
        record.setTrans_status(transStatus);
        record.setTrans_remarks(transRemarks);
        transationDao.save(record);
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

    public String  getRandomNo(){
            String sources = "123456789abcdefghijklmpqrstuvwxyz"; // 加上一些字母，就可以生成pc站的验证码了
            Random rand = new Random();
            StringBuffer flag = new StringBuffer();
            for (int j = 0; j < 6; j++) {
                flag.append(sources.charAt(rand.nextInt(32)) + "");
            }
        return flag.toString();
    }
}
