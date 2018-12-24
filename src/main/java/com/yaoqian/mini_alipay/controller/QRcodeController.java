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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import sun.misc.BASE64Encoder;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
@RestController
public class QRcodeController {

    @Autowired
    private QRcodeServer QRcodeServer;
    @Authorization
    @RequestMapping(value = { "/Qrcode" }, method = RequestMethod.POST)
    public ResultEntity productcode(@CurrentUser UserEntity currentuser,HttpServletResponse response) {
        /*if (null == currentuser.getUid()) {
            return ResultTools.result(1001, "uid is empty", null);
        }*/
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        BufferedImage image = QRcodeServer.zxingCodeCreate(currentuser.getUsername(), 200,null);
        response.setContentType("image/png");
        try{
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os);
            BASE64Encoder encoder = new BASE64Encoder();
            String base64Img = encoder.encode(os.toByteArray());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", base64Img);
            return ResultTools.result(0, "",map);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultTools.result(404, "",null);
    }

    @Authorization
    @RequestMapping(value = { "/AAQrcode" }, method = RequestMethod.POST)
    public ResultEntity productAAcode(@CurrentUser UserEntity currentuser,Float coin,HttpServletResponse response, HttpSession session) {

        BufferedImage image = QRcodeServer.zxingCodeCreate(currentuser.getUsername()+"|"+coin, 200,null);
        response.setContentType("image/png");
        try{
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os);
            BASE64Encoder encoder = new BASE64Encoder();
            String base64Img = encoder.encode(os.toByteArray());
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("content", base64Img);
            return ResultTools.result(0, "",map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultTools.result(404, "",null);
    }
}
