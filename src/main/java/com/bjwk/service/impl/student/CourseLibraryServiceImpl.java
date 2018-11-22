package com.bjwk.service.impl.student;

import com.bjwk.dao.AppConfigDao;
import com.bjwk.dao.CourseLibraryDao;
import com.bjwk.dao.RegLoginDao;
import com.bjwk.model.CourseVideoBankCatalogDTO;
import com.bjwk.model.CourseVideoBankDetailVO;
import com.bjwk.model.CourseVideoBankVO;
import com.bjwk.model.pojo.UserCollection;
import com.bjwk.service.publics.reglogin.RegLoginService;
import com.bjwk.service.student.CourseLibraryService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.RedisClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: family-school
 * @description: 课程库业务逻辑处理
 * @author: liqitian.
 * @create: 2018-09-08 17:15
 **/
@Service
@Slf4j
public class CourseLibraryServiceImpl implements CourseLibraryService {

    @Autowired
    private CourseLibraryDao courseLibraryDao;

    @Autowired
    private AppConfigDao appConfigDao;

    @Autowired
    private RegLoginService regLoginService;


    @Override
    public DataWrapper<Object> getCourseClassType() {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();
        String strConfig = appConfigDao.getCourseClassType();
        Gson gson = new Gson();

        Map map ;
        map = gson.fromJson(strConfig, Map.class);

        dataWrapper.setData(map);
        return dataWrapper;
    }


    /**
     * @Description:查询视屏课程列表
     * @Param: [categoryId, isCharge]
     * @Author: liqitian
     * @Date: 2018/9/9
     */
    @Override
    public DataWrapper<PageInfo<CourseVideoBankVO>> queryVideoCourse(
            String categoryId, Integer isCharge, int currentPage, int numberPerPage, String token
    ) {

        DataWrapper<PageInfo<CourseVideoBankVO>> dataWrapper = new DataWrapper<PageInfo<CourseVideoBankVO>>();

        PageHelper.startPage(currentPage, numberPerPage);
        List<CourseVideoBankVO> courseVideoBankVOList = courseLibraryDao.queryVideoCourse(categoryId.split(","), isCharge);

        //检查课程是否被收藏
        boolean flag = checkCourseIsCollection(courseVideoBankVOList, token);
        if (!flag) {
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setMsg("该用户未登录");
            return dataWrapper;
        }
        PageInfo<CourseVideoBankVO> page = new PageInfo<CourseVideoBankVO>(courseVideoBankVOList);
        dataWrapper.setData(page);
        return dataWrapper;
    }

    /**
     * @Description:查看视屏课程详情
     * @Param: [courseVideoBankId]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Object>
     * @Author: liqitian
     * @Date: 2018/9/10
     */
    @Override
    public DataWrapper<Object> queryVideoDetails(Integer courseVideoBankId, String token) {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();
        CourseVideoBankDetailVO courseVideoBankDetailVO = courseLibraryDao.queryVideoDetails(courseVideoBankId);
        Jedis jedis = RedisClient.getJedis();
        Long browseNumber = jedis.hincrBy("videoBrowseNumber", String.valueOf(courseVideoBankId), 1);
        jedis.close();
        courseVideoBankDetailVO.setBrowseNumber(browseNumber);
        //是否收藏
        if (token == null) {
            courseVideoBankDetailVO.setIsCollection(false);
        } else {
            String userId = regLoginService.getUserIdByToken(token);
            if (userId == null) {
                courseVideoBankDetailVO.setIsCollection(false);
            } else {
                if (courseLibraryDao.queryIsCollection(userId, courseVideoBankId, 0) != null) {
                    courseVideoBankDetailVO.setIsCollection(true);
                } else {
                    courseVideoBankDetailVO.setIsCollection(false);
                }

            }
        }
        //目录
        List<CourseVideoBankCatalogDTO> catalogList = courseLibraryDao.queryVideoCourseCatalog(courseVideoBankDetailVO.getPackageNum());
        HashMap<String, Object> hm = new HashMap<String, Object>(2);
        hm.put("videoCourseDetail", courseVideoBankDetailVO);
        hm.put("catalogList", catalogList);
        dataWrapper.setData(hm);
        return dataWrapper;
    }

    /**
     * @Description:删除视屏课程
     * @Param: [courseVideoBankId] 字符串 拼接 1,2,4
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/10
     */
    @Override
    public DataWrapper<Void> deleteVideoCourse(String courseVideoBankIds) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();

        String[] courseVideoBankStrIds = courseVideoBankIds.split(",");
        courseLibraryDao.deleteVideoCourse(courseVideoBankStrIds);
        dataWrapper.setMsg("删除成功");
        return dataWrapper;

    }

    @Override
    public DataWrapper<Void> test(String testname, String testpassword) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        courseLibraryDao.test(testname,testpassword);
        dataWrapper.setMsg("测试新增成功");
        return dataWrapper;
    }

    /**
     * @Description "下载"
     * @Date 2018/11/22 15:10
     * @Param [videoUrl]
     * @return com.bjwk.utils.DataWrapper<java.lang.Void>
     */
    @Override
    public void downLoadVideoCourse(Integer courseVideoBankId, HttpServletResponse response) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        /**
         * 查询课程相关信息
         */
        CourseVideoBankDetailVO courseVideoBankDetailVO = courseLibraryDao.queryVideoDetails(courseVideoBankId);

        httpDownload(courseVideoBankDetailVO.getVideo(),response,courseVideoBankDetailVO.getTitle());
    }

    public  void httpDownload(String httpUrl, HttpServletResponse response,String title) {
        // 1.下载网络文件
        int byteRead;
        URL url = null;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        InputStream inStream = null;
        OutputStream fs = null;
        try {
            //2.获取链接
            URLConnection conn = url.openConnection();
            //3.输入流
             inStream = conn.getInputStream();



            response.setContentType("text/html;charset=UTF-8");
            response.setContentType("application/octet-stream; charset=utf-8");
//new String(fileName.getBytes("gb2312"),"ISO-8859-1") 中文编码，防止乱码
//filename用""引起来，防止火狐截断
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + new String(title.getBytes("gb2312"),"ISO-8859-1") + "\"");

            //response.setContentType("video/avi");
            //String  fileName = title +".avi";
            //response.addHeader("Content-Disposition", "attachment;filename="+fileName);
            //System.out.println(fileName);
             fs = response.getOutputStream();
            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 检查该用户是否对课程进行收藏
     *
     * @param courseVideoBankVOList
     * @param token
     */
    public boolean checkCourseIsCollection(List<CourseVideoBankVO> courseVideoBankVOList, String token) {
        if (token != null) {
            String userId = regLoginService.getUserIdByToken(token);
            log.error("userId : " + userId);
            if (userId == null) {
                return false;
            }
            //query user_collection  type == 1
            List<UserCollection> userCollectionPOList = courseLibraryDao.queryVideoCollection(userId);
            if (userCollectionPOList != null && !userCollectionPOList.isEmpty()) {
                for (UserCollection userCollection : userCollectionPOList) {
                    for (CourseVideoBankVO courseVideoBankVO : courseVideoBankVOList) {
                        if (userCollection.getThingId() == courseVideoBankVO.getCourseVideoBankId()) {
                            courseVideoBankVO.setIsCollection(true);
                        }
                    }
                }
            } else {
                checkCourseIsCollection(courseVideoBankVOList, null);
            }
        } else {
            for (CourseVideoBankVO courseVideoBankVO : courseVideoBankVOList) {
                courseVideoBankVO.setIsCollection(false);
            }
        }
        return true;
    }
}