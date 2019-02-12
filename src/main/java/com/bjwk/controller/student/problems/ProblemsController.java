/*
 * Copyright (c)  2019
 * All rights reserved.
 *
 * Id:ProblemsController.java   2019-02-12 11:44 wangsui
 */
package com.bjwk.controller.student.problems;

import com.bjwk.dao.RegLoginDao;
import com.bjwk.model.problems.Problems;
import com.bjwk.service.student.problems.ProblemsService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.RedisClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author: wangsui
 * @description：${description}
 * @create: 2019-02-12 11:44
 * @version：1.0
 */

@Slf4j
@RestController
@RequestMapping("api/student/problems")
public class ProblemsController {

    @Autowired
    private ProblemsService problemsService;

    /**
     * @return :
     * @Desc : 查询题库题目
     * @Param :
     **/
    @RequestMapping("/_list")
    public DataWrapper<PageInfo<Problems>> list(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "problemDetails") String problemDetails,
            @RequestParam(value = "isSingleElection") String isSingleElection,
            @RequestParam(value = "facilityValue") String facilityValue,
            @RequestParam(value = "grade") String grade,
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "problemType") String problemType,
            @RequestParam(value = "numberPerPage", defaultValue = "1") Integer numberPerPage,
            @RequestParam(value = "currentPage", defaultValue = "10") Integer currentPage) {
        log.info("接收到的参数为：题目类型：" + problemDetails + "，是否单选题：" + isSingleElection + "，难易程度：" + facilityValue + ",年级：" + grade + ",科目：" + subject);
        DataWrapper<PageInfo<Problems>> dataWrapper = new DataWrapper<PageInfo<Problems>>();
        if (numberPerPage <= 0 || currentPage <= 0) {
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setMsg("传递的数据有误");
            return dataWrapper;
        }

        Jedis jedis = RedisClient.getJedis();
        String userName = (String) jedis.hget("loginStatus", token);
        if(StringUtils.isEmpty(userName)){
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setMsg("未登录");
            return dataWrapper;

        }
        PageHelper.startPage(currentPage, numberPerPage);

        Problems pojo = new Problems();
        if (!StringUtils.isEmpty(problemDetails)) {
            pojo.setProblemDetails(problemDetails);

        }
        if (!StringUtils.isEmpty(isSingleElection)) {
            pojo.setProblemDetails(isSingleElection);

        }
        if (!StringUtils.isEmpty(facilityValue)) {
            pojo.setProblemDetails(facilityValue);

        }
        if (!StringUtils.isEmpty(grade)) {
            pojo.setProblemDetails(grade);

        }
        if (!StringUtils.isEmpty(subject)) {
            pojo.setProblemDetails(subject);

        }
        if (!StringUtils.isEmpty(problemType)) {
            pojo.setProblemDetails(problemType);

        }
        List<Problems> problemsList = this.problemsService.select(pojo);
        if(CollectionUtils.isEmpty(problemsList)){
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setMsg("暂未有该数据");
        }else{
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            dataWrapper.setMsg("查询成功");
            PageInfo<Problems> page = new PageInfo<Problems>(problemsList);
            dataWrapper.setData(page);
        }

        return dataWrapper;
    }

}
