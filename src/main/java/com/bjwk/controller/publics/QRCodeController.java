package com.bjwk.controller.publics;


import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.annotation.MyLog;
import com.google.zxing.WriterException;
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



    /**
     * @Description:生成二维码
     * @Param: [response, url]
     * @return: void
     * @Author: liqitian
     * @Date: 2018/11/1
     */

    @RequestMapping("/getQrCode")
    @ResponseBody
    @MyLog
    public void addSchoolNews(
            HttpServletResponse response,
            @RequestParam("url") String url
    ) throws WriterException {
        new ZXingCodeUtil().getCodeTest(response, url);
    }
}
