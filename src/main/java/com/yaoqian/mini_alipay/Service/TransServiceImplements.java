package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.TransactionEntity;
import com.yaoqian.mini_alipay.entity.NoticeEntity;
import com.yaoqian.mini_alipay.mapper.NoticeDao;
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
    @Autowired
    private NoticeDao noticeDao;


    @Override
    public void CreateRecord(String transUid, String Trans_name,String transObjUid, String Trans_obj_name, Integer transType, Float amount, Integer transStatus, Integer transCategoryId, String transRemarks){
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
        record.setTrans_name(Trans_name);
        record.setTrans_obj_id(transObjUid);
        record.setTrans_obj_name(Trans_obj_name);
        record.setTrans_status(transStatus);
        record.setTrans_remarks(transRemarks);
        transationDao.save(record);

        //记录通知
        NoticeEntity notice = new NoticeEntity();
        notice.setNotice_uid("0");
        notice.setNotice_to_uid(transUid);
        notice.setNotice_time(a.substring(11));
        notice.setNotice_read(1);
        notice.setNotice_type(1);
        if(transType==0) {
            notice.setNotice_text("向" + Trans_obj_name + "转账" + amount + "元");
            noticeDao.save(notice);
        }
            //noticeMapper.addNotice("0",transUid,a.substring(11),'向'+Trans_obj_name+"转账"+amount+"元",1,1);
        else{
            notice.setNotice_text("收到"+Trans_obj_name+"转账"+amount+"元");
            noticeDao.save(notice);
        }
            //noticeMapper.addNotice("0",transUid,a.substring(11),"收到"+Trans_obj_name+"转账"+amount+"元",1,1);
    }

    @Override
    public void SuccesRecord(String transUid, String Trans_name, String transObjUid, String Trans_obj_name, Integer transType, Float amount, Integer transCategoryId){
        CreateRecord(transUid, Trans_name, transObjUid, Trans_obj_name,transType, amount, 0, transCategoryId, "成功");
    }

    @Override
    public void FailRecord(String transUid, String Trans_name, String transObjUid, String Trans_obj_name, Integer transType, Float amount, Integer transCategoryId, String transRemarks){
        CreateRecord(transUid, Trans_name, transObjUid, Trans_obj_name, transType, amount, 1, transCategoryId, transRemarks);
    }

    @Override
    public void TwoSuccessTransferRecord(String transOutUid, String Trans_name, String transInUid, String Trans_obj_name, Float amount){
        SuccesRecord(transOutUid, Trans_name, transInUid, Trans_obj_name,0, amount, 1);
        SuccesRecord(transInUid, Trans_name, transOutUid,Trans_obj_name,  1, amount, 1);
    }

    @Override
    public void TwoFailTransferRecord(String transOutUid, String Trans_name, String transInUid, String Trans_obj_name, Float amount, String transRemarks){
        FailRecord(transOutUid, Trans_name, transInUid, Trans_obj_name, 0, amount, 1, transRemarks);
        FailRecord(transInUid, Trans_name, transOutUid, Trans_obj_name,1, amount, 1, transRemarks);
    }

    private String  getRandomNo(){
            String sources = "123456789abcdefghijklmpqrstuvwxyz"; // 加上一些字母，就可以生成pc站的验证码了
            Random rand = new Random();
            StringBuffer flag = new StringBuffer();
            for (int j = 0; j < 6; j++) {
                flag.append(sources.charAt(rand.nextInt(32)) + "");
            }
        return flag.toString();
    }
}
