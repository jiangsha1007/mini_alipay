package com.yaoqian.mini_alipay.controller;

import com.yaoqian.mini_alipay.Service.QRcodeServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/Qrcode/{id}")
    public void productcode(@PathVariable("id") String id, HttpServletResponse response, HttpSession session) {
        BufferedImage image = QRcodeServer.zxingCodeCreate(id, 200,null);
        response.setContentType("image/png");
        try{
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
