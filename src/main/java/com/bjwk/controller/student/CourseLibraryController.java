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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @Param: [test1]
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
            @RequestParam(value = "currentPage", required = false,defaultValue = "1") int currentPage,
            @RequestParam(value = "numberPerPage", required = false,defaultValue = "10") int numberPerPage
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


    /**
     * @Description "下载视屏课程"
     * @Date 2018/11/22 15:09
     * @Param [videoUrl]
     * @return com.bjwk.utils.DataWrapper<java.lang.Void>
     */
    @RequestMapping("/downLoadVideoCourse")
    @ResponseBody
    @MyLog
    @TokenValidate
    public DataWrapper<Void> downLoadVideoCourse(
            @RequestParam(value = "token") String token,
            @RequestParam("courseVideoBankId") Integer courseVideoBankId,
            @RequestParam("courseDownLoadPath") String courseDownLoadPath
    ) {
         return courseLibraryService.downLoadVideoCourse(token,courseVideoBankId,courseDownLoadPath);
    }

    /**
     * @Description "我的下载视屏列表"
     * @Date 2018/11/22 15:09
     * @Param [videoUrl]
     * @return com.bjwk.utils.DataWrapper<java.lang.Void>
     */
    @RequestMapping("/downLoadVideoCourseList")
    @ResponseBody
    @MyLog
    @TokenValidate
    public DataWrapper<Object> downLoadVideoCourseList(
            @RequestParam(value = "token") String token
    ) {
        return courseLibraryService.downLoadVideoCourseList(token);
    }

    /**
     * @Description "查看我的课程"
     * @Date 2018/11/22 16:48
     * @Param [token]
     * @return void
     */
    @RequestMapping("/queryMyCourseList")
    @ResponseBody
    @MyLog
    @TokenValidate
    public DataWrapper<PageInfo<CourseVideoBankVO>> queryMyCourseList(
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "currentPage", required = false,defaultValue = "1") int currentPage,
             @RequestParam(value = "numberPerPage", required = false,defaultValue = "10") int numberPerPage
    ) {
        return courseLibraryService.queryMyCourseList(token,currentPage,numberPerPage);
    }

    /**
     * @Description "关键字检索视屏课程库课程"
     * @Date 2018/11/22 16:48
     * @Param [token]
     * @return void
     */
    @RequestMapping("/queryCourseByKeyword")
    @ResponseBody
    @MyLog
    @TokenValidate
    public DataWrapper<Object> queryCourseByKeyword(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "scrollId", required = false) String scrollId
    ) {
        return courseLibraryService.queryCourseByKeyword(keyword,scrollId);
    }

}
