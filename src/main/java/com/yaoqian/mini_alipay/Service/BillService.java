package com.yaoqian.mini_alipay.Service;

import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;

public interface BillService {

    ResultEntity QueryTransListByTime(UserEntity currentuser, Integer page, Integer pageSize, Integer time_type, String time_start, String time_end, Integer trans_category, Integer trans_type);

    ResultEntity QueryTransDetail(Integer Trans_id);

}
