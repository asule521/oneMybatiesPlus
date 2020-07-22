package com.zjx.controller;

import com.zjx.Utils.CodeModel;
import com.zjx.Utils.QR_Code;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("erweima")
public class Erweima {
    @ResponseBody
    @RequestMapping(value = "/addqrcode")
    public String addqrcode(HttpServletRequest request) throws Exception {
        String realPath = "D:\\qrcode\\";
        String  msg = String.valueOf(System.currentTimeMillis()) + ".png";
        CodeModel info = new CodeModel();
       /* info.setContents("客户:" + request.getParameter("customer") + " 品牌:" + request.getParameter("brand") + " 型号:"
                + request.getParameter("model") + " 日期:" + request.getParameter("addtime") + " 检验员:"
                + request.getParameter("testpersion"));*/
        info.setContents("找我吃饭吧，不然感情都淡了");
       /* info.setWidth(200);
        info.setHeight(200);
        info.setFontSize(20);*/
        info.setLogoFile(new File(realPath + msg));
       /* info.setDate("         2020-07-14      ");
        info.setDesc("          测试         ");
        info.setFontSize(15);*/
      //  info.setLogoFile(new File("D:\\qrcode\\111.jpg"));

        QR_Code code = new QR_Code();

        code.createCodeImage(info, realPath + msg);
        return msg;
    }
}
