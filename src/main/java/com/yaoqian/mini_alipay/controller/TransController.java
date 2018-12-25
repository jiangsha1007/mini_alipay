package com.yaoqian.mini_alipay.controller;

import com.yaoqian.mini_alipay.Service.NoticeService;
import com.yaoqian.mini_alipay.Service.TransService;
import com.yaoqian.mini_alipay.Service.UserService;
import com.yaoqian.mini_alipay.annotation.Authorization;
import com.yaoqian.mini_alipay.annotation.CurrentUser;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.yaoqian.mini_alipay.entity.NoticeEntity;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@RestController(value="/trans")
public class TransController {

    @Autowired
    private TransService transService;

    @Autowired
    private UserService userService;

    @Autowired
    private NoticeService noticeService;
    /***
     * 转账
     * @param out_user
     * @param in_usrname
     * @param amount
     * @return ResultEntity
     * @throws Exception
     */
    @Authorization
    @Transactional
    @PostMapping(value = "/transfer")
    public ResultEntity UserTransfer(@CurrentUser UserEntity out_user,
                                     @RequestParam("in_username") String in_usrname,
                                     @RequestParam("amount") Float amount) throws Exception {
        UserEntity in_user = userService.findByUsername(in_usrname);
        if (out_user != null && in_user != null) {
            if (out_user.getBalance() >= amount) {
                out_user.setBalance(out_user.getBalance() - amount);
                in_user.setBalance(in_user.getBalance() + amount);
                userService.save(out_user);
                userService.save(in_user);
                //save transaction records
                transService.TwoSuccessTransferRecord(out_user.getUid(), out_user.getUsername(), in_user.getUid(), in_user.getUsername(), amount);

                return ResultTools.success("转账成功");
            } else {
                transService.TwoFailTransferRecord(out_user.getUid(), out_user.getUsername(), in_user.getUid(), in_user.getUsername(), amount, "余额不足");
                throw new Exception("余额不足");
            }
        } else {
            transService.TwoFailTransferRecord(out_user.getUid(), out_user.getUsername(), null, null, amount, "用户名不存在");
            throw new Exception("用户名不存在！");
        }
    }

    @Authorization
    @Transactional
    @PostMapping(value = "/notice")
    public ResultEntity GetTransferNotice(@CurrentUser UserEntity currentuser) throws Exception {
        try {
            if (null == currentuser.getUid()) {
                return ResultTools.result(1001, "uid is empty", null);
            }

            NoticeEntity Noticelist;
            Noticelist = noticeService.queryNotice(currentuser.getUid());
            Map<String, Object> map = new HashMap<String, Object>();
            if (Noticelist == null) {
                map.put("count", 0);
                map.put("content", "");
            } else {
                map.put("count", 1);
                map.put("content", Noticelist);
                noticeService.updateNotice(Noticelist.getNotice_id());

            }
            return ResultTools.result(0, "", map);

        } catch (Exception e) {
            return ResultTools.result(404, e.getMessage(), null);
        }
    }
}