package com.bjwk.controller.publics;

import com.bjwk.service.UserAttendServie;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.annotation.MyLog;
import com.bjwk.utils.annotation.TokenValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: family-school
 * @description: 用户签到相关接口
 * @author: liqitian.
 * @create: 2018-09-12 20:34
 **/
@Controller
@RequestMapping("api/userAttend")
public class UserAttendController {

    @Autowired
    private UserAttendServie userAttendServie;

    /**
     * @Description:用户签到
     * @Param: [token]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/12
     */

    @RequestMapping("/attend")
    @ResponseBody
    @MyLog
    @TokenValidate
    public DataWrapper<Void> attend(
            @RequestParam("token") String token,
            @RequestParam("attendType") Integer attendType
    ) {

        return userAttendServie.attend(token,attendType);
    }

    /**
     * @Description: 查看用户签到情况
     * @Param: [token, month]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/12
     */
    @TokenValidate
    @ResponseBody
    @MyLog
    @RequestMapping("/queryAttendSituation")
    public DataWrapper<List<String>> queryAttendSituation(
            @RequestParam("token") String token,
            @RequestParam("attendType") Integer attendType,
            @RequestParam("month") String month
    ) {
        return userAttendServie.queryAttendSituation(token,attendType,month);
    }
}
