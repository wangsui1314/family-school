package com.bjwk.controller;


import com.bjwk.utils.annotation.MyLog;
import com.bjwk.utils.qrcode.ZXingCodeUtil;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Controller
public class BuildController {


    /**
     * @Description:build
     * @Param: [response, url]
     * @return: void
     * @Author: liqitian
     * @Date: 2018/11/1
     */

    @RequestMapping("build")
    @ResponseBody
    public String addSchoolNews(
    ) {

        String cmd = "cd /root/family-school && make";
        System.out.println("got cmd job : " + cmd);
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec(cmd);
            InputStream in = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[1024];
            for (int n; (n = in.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }
            System.out.println("job result [" + out.toString() + "]");
            in.close();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "build success";
    }

}
