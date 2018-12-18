package com.bjwk.controller.publics;

import com.bjwk.utils.PushUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: family-school
 * @description: {类描述}
 * @author: liqitian3344
 * @create: 2018-12-17 16:02
 */
@Controller
@RequestMapping("api/push")
public class PushTestController {


    @RequestMapping("api/p")
    public String push(){
        return "ok";
    }
}
