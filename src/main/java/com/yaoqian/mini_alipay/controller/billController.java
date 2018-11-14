package com.yaoqian.mini_alipay.controller;
import com.yaoqian.mini_alipay.mapper.TransactionMapper;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.tools.ResultTools;
import com.yaoqian.mini_alipay.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
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
    public ResultEntity QueryTransListByTime(String uid, Integer page, Integer pageSize) {
        try {
            if (null == uid) {
                return ResultTools.result(1001, "uid is empty", null);
            }
            PageHelper.startPage(page, pageSize);// 分页配置
            List<TransactionEntity> Translist = TransactionMapper.QueryTranslistByTime(uid);
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
