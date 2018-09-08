package com.bjwk.controller.student;

import com.bjwk.service.student.CourseLibraryService;
import com.bjwk.utils.DataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: family-school
 * @description: 课程库相关api
 * @author: liqitian.
 * @create: 2018-09-08 17:12
 **/
@Controller
@RequestMapping("api/courseLibrary")
@Slf4j
public class CourseLibraryController {

    @Autowired
    private CourseLibraryService courseLibraryService;

    /**
     * @Description:课程分类图
     * @Param: []
     * @return: com.bjwk.utils.DataWrapper<java.lang.Object>
     * @Author: liqitian
     * @Date: 2018/9/8
     */
    @RequestMapping("/getCourseClassType")
    @ResponseBody
    public DataWrapper<Object> getCourseClassType() {
        System.out.println("123");
        return courseLibraryService.getCourseClassType();
    }
}
