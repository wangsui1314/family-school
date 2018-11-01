package com.bjwk.controller.publics;


import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.annotation.MyLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bjwk.utils.qrcode.ZXingCodeUtil;

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
            ZXingCodeUtil.getCodeTest(response);
        }catch(Exception e){
        }

    }
}
