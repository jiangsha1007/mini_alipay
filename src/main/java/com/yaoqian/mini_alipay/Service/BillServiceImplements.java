package com.yaoqian.mini_alipay.Service;

import com.github.pagehelper.PageHelper;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.TransactionEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;
import com.yaoqian.mini_alipay.mapper.TransactionMapper;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BillServiceImplements implements BillService {

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public ResultEntity QueryTransListByTime(UserEntity currentuser, Integer page, Integer pageSize, Integer time_type, String time_start, String time_end, Integer trans_category, Integer trans_type){
        if (null == currentuser.getUid()) {
            return ResultTools.result(1001, "uid is empty", null);
        }
        if(pageSize==null)
            pageSize=20;
        if(page==null)
            page=0;
        PageHelper.startPage(page, pageSize);// 分页配置
        String trans_type_str;
        if(trans_type== null)
            trans_type_str = "(0,1)";
        else
            trans_type_str = "("+trans_type+")";
        List<TransactionEntity> Translist;
        if(time_type==null)
            time_type=99;
        /*1 按月 2 按日 */
        if(time_type==1){
            /*按照xxxx-xx格式* 个位不加0*/
            int year= Integer.parseInt(time_start.split("-")[0]);
            int month= Integer.parseInt(time_start.split("-")[1]);
            Translist = transactionMapper.QueryTranslistByTime_y(currentuser.getUid(),year,month,trans_type_str);
        }
        else if(time_type==2){
            /*按照xxxx-xx-xx格式 个位加0*/
            Translist = transactionMapper.QueryTranslistByTime_d(currentuser.getUid(),time_start,time_end,trans_type_str);
        }
        else {
            Translist = transactionMapper.QueryTranslistByTime(currentuser.getUid());
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", Translist.size());
        map.put("page", page);
        map.put("content", Translist);
        return ResultTools.result(0, "",map);
    }

    @Override
    public ResultEntity QueryTransDetail(Integer Trans_id){
        if (null == Trans_id) {
            return ResultTools.result(1001, "Trans_id is empty", null);
        }
        TransactionEntity Translist;
        Translist = transactionMapper.QueryTransDetail(Trans_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("count", 1);
        map.put("content", Translist);
        return ResultTools.result(0, "",map);
    }
}
