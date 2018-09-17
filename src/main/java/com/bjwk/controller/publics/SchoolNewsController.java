package com.bjwk.controller.publics;

import com.bjwk.service.publics.SchoolNewsService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.annotation.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: family-school
 * @description: 校园新闻api
 * @author: liqitian.
 * @create: 2018-09-17 19:27
 **/
@Controller
@RequestMapping("api/schoolNews")
public class SchoolNewsController {

    @Autowired
    private SchoolNewsService schoolNewsService;


    /**
     * @Description:新增校园新闻
     * @Param: [newsTitle, newsContext, newsImage]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/17
     */
    @RequestMapping("/addSchoolNews")
    @ResponseBody
    @MyLog
    public DataWrapper<Void> addSchoolNews(
        @RequestParam("newsTitle") String newsTitle,
        @RequestParam("newsContext") String newsContext,
        @RequestParam("newsImage") String newsImage
    ){
        return schoolNewsService.addSchoolNews(newsTitle,newsContext,newsImage);
    }


    /**
     * @Description:更新校园新闻
     * @Param: [id, newsTitle, newsContext, newsImage]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/17
     */
    @RequestMapping("/updateSchoolNews")
    @ResponseBody
    @MyLog
    public DataWrapper<Void> updateSchoolNews(
            @RequestParam("id") Integer id,
            @RequestParam("newsTitle") String newsTitle,
            @RequestParam("newsContext") String newsContext,
            @RequestParam("newsImage") String newsImage
    ){

        return schoolNewsService.updateSchoolNews(id,newsTitle,newsContext,newsImage);
    }

    /**
     * @Description:删除校园新闻
     * @Param: [ids]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/17
     */
    @RequestMapping("/deleteSchoolNews")
    @ResponseBody
    @MyLog
    public DataWrapper<Void> deleteSchoolNews(
            @RequestParam("ids") String ids
    ){
        return schoolNewsService.deleteSchoolNews(ids);
    }


    /**
     * @Description:查看校园新闻
     * @Param: [id]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/17
     */
    @RequestMapping("/querySchoolNews")
    @ResponseBody
    @MyLog
    public DataWrapper<Object> querySchoolNews(
            @RequestParam(value = "id",required = false) String id,
            @RequestParam(value = "currentPage", required = false,defaultValue = "1") int currentPage,
            @RequestParam(value = "numberPerPage", required = false,defaultValue = "10") int numberPerPage
    ){
        return schoolNewsService.querySchoolNews(id,currentPage,numberPerPage);
    }
}
