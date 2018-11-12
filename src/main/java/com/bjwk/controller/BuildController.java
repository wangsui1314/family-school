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
import java.util.ArrayList;
import java.util.List;

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
    public List<String> addSchoolNews(
    ) {

        String cmd = "cd /root/family-school && make";
        System.out.println("got cmd job : " + cmd);
        Runtime run = Runtime.getRuntime();
        try {
//            Process process = run.exec(cmd);
            Process process = run.exec(new String[] {"/bin/sh", "-c", cmd});
            InputStream in = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            List<String> list = new ArrayList<String>();
            String result = null;
            while ((result = bs.readLine()) != null) {
                System.out.println("job result [" + result + "]");
                list.add(result);
            }
            in.close();
            // process.waitFor();
            process.destroy();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @RequestMapping("queryTest")
    @ResponseBody
    public String queryTest(
    ) {
        return "build ok";
    }

}
