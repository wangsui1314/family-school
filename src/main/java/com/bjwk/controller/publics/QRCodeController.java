package com.bjwk.controller.publics;


import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.annotation.MyLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bjwk.utils.qrcode.ZXingCodeUtil;
import java.io.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("api/qrcode")
public class QRCodeController {

    @RequestMapping("/getQrCode")
    @ResponseBody
    @MyLog
    public void addSchoolNews (
            HttpServletResponse response
    ){
        try{
            new ZXingCodeUtil().getCodeTest(response);
        }catch(Exception e){
        }

    }
//
//
//    @RequestMapping("/getQrCodey")
//    @ResponseBody
//    @MyLog
//    public void addSchoolNews (
//            HttpServletResponse response
//    ){
//        FileInputStream fis = null;
//        response.setContentType("image/gif");
//        try {
//            OutputStream out = response.getOutputStream();
//            File file = new File("C://Users///HLi78//Desktop//docker.png");
//            fis = new FileInputStream(file);
//            byte[] b = new byte[fis.available()];
//            fis.read(b);
//            out.write(b);
//            out.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
