package com.yaoqian.mini_alipay.controller;

import com.yaoqian.mini_alipay.Service.QRcodeServer;
import com.yaoqian.mini_alipay.annotation.Authorization;
import com.yaoqian.mini_alipay.annotation.CurrentUser;
import com.yaoqian.mini_alipay.entity.ResultEntity;
import com.yaoqian.mini_alipay.entity.UserEntity;
import com.yaoqian.mini_alipay.tools.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class QRcodeController {

    @Autowired
    private QRcodeServer QRcodeServer;
    @Authorization
    @RequestMapping(value = { "/Qrcode" }, method = RequestMethod.POST)
    public void productcode(@CurrentUser UserEntity currentuser,HttpServletResponse response) {
        /*if (null == currentuser.getUid()) {
            return ResultTools.result(1001, "uid is empty", null);
        }*/
        BufferedImage image = QRcodeServer.zxingCodeCreate(currentuser.getUid(), 200,null);
        response.setContentType("image/png");
        try{
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void productAAcode(@CurrentUser UserEntity currentuser,Float coin,HttpServletResponse response, HttpSession session) {
        BufferedImage image = QRcodeServer.zxingCodeCreate(currentuser.getUid()+"|"+coin, 200,null);
        response.setContentType("image/png");
        try{
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
