package com.bjwk.controller.student;

import com.bjwk.model.CourseVideoBankVO;
import com.bjwk.service.student.CourseLibraryService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.annotation.AdminTokenValidate;
import com.bjwk.utils.annotation.MyLog;
import com.bjwk.utils.annotation.TokenValidate;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    @MyLog
    public DataWrapper<Object> getCourseClassType() {
        System.out.println("123");
        return courseLibraryService.getCourseClassType();
    }

    /**
     * @Description:查询视屏课程列表
     * @Param: [categoryId, isCharge]
     * @return: com.bjwk.utils.DataWrapper<List       <       CourseVideoBankVO>>
     * @Author: liqitian
     * @Date: 2018/9/9
     */
    @RequestMapping("/queryVideoCourse")
    @ResponseBody
    @MyLog
    @TokenValidate
    public DataWrapper<PageInfo<CourseVideoBankVO>> queryVideoCourse(
            @RequestParam(value = "token", required = false) String token,
            @RequestParam("categoryId") String categoryId,
            @RequestParam(value = "isCharge", required = false, defaultValue = "0") Integer isCharge,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "numberPerPage", defaultValue = "10") int numberPerPage
    ) {

        return courseLibraryService.queryVideoCourse(categoryId, isCharge, currentPage, numberPerPage, token);
    }

    /**
     * @Description:查询视屏课程详情
     * @Param: [courseVideoBankId]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Object>
     * @Author: liqitian
     * @Date: 2018/9/10
     */
    @RequestMapping("/queryVideoDetails")
    @ResponseBody
    @MyLog
    @TokenValidate
    public DataWrapper<Object> queryVideoDetails(
            @RequestParam("courseVideoBankId") Integer courseVideoBankId,
            @RequestParam(value = "token", required = false) String token
    ) {
        return courseLibraryService.queryVideoDetails(courseVideoBankId, token);
    }

    /**
     * @Description:删除视屏课程
     * @Param: [courseVideoBankId] 字符串 拼接 1,2,4
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/10
     */
    @RequestMapping("/deleteVideoCourse")
    @ResponseBody
    @MyLog
    //@AdminTokenValidate
    public DataWrapper<Void> deleteVideoCourse(
            @RequestParam("courseVideoBankIds") String courseVideoBankIds
    ) {
        return courseLibraryService.deleteVideoCourse(courseVideoBankIds);
    }
}
