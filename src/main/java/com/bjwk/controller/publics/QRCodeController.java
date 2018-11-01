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
            HttpServletResponse response,
            @RequestParam("url") String url
    ){
        try{
            new ZXingCodeUtil().getCodeTest(response,url);
        }catch(Exception e){
        }

    }
}
