package com.yaoqian.mini_alipay.controller;

import com.yaoqian.mini_alipay.Service.BillService;
import com.yaoqian.mini_alipay.annotation.Authorization;
import com.yaoqian.mini_alipay.annotation.CurrentUser;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bill")
public class billController {

    @Autowired
    private BillService billService;

    @Authorization
    @RequestMapping(value = { "/trans" }, method = RequestMethod.POST)
    public ResultEntity QueryTransListByTime(@CurrentUser UserEntity currentuser, Integer page, Integer pageSize, Integer time_type, String time_start, String time_end, Integer trans_category, Integer trans_type) {
        try {
            return billService.QueryTransListByTime(currentuser, page, pageSize, time_type, time_start,time_end, trans_category, trans_type);
        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }

    @Authorization
    @RequestMapping(value = { "/QueryTransDetail" }, method = RequestMethod.POST)
    public ResultEntity QueryTransDetail(Integer Trans_id) {
        try {
            return billService.QueryTransDetail(Trans_id);
        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }
}
