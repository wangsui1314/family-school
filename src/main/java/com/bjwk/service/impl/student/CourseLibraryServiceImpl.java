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
import com.bjwk.utils.JestClientConfiguration;
import com.bjwk.utils.RedisClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.ClearScroll;
import io.searchbox.core.Search;
import io.searchbox.core.SearchScroll;
import io.searchbox.params.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 搜索上下文的时间,用来支持该批次
     */
    private static final String SCROLL_ALIVE_TIME = "1m";

    @Autowired
    private CourseLibraryDao courseLibraryDao;

    @Autowired
    private AppConfigDao appConfigDao;

    @Autowired
    private RegLoginService regLoginService;

    @Autowired
    private JestClientConfiguration jestClientConfiguration;



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



    /**
     * @Description "下载"
     * @Date 2018/11/22 15:10
     * @Param [videoUrl]
     * @return com.bjwk.utils.DataWrapper<java.lang.Void>
     */
    @Override
    public void downLoadVideoCourse(Integer courseVideoBankId, HttpServletResponse response, HttpServletRequest request) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        /**
         * 查询课程相关信息
         */
        CourseVideoBankDetailVO courseVideoBankDetailVO = courseLibraryDao.queryVideoDetails(courseVideoBankId);

        httpDownload(courseVideoBankDetailVO.getVideo(),response,courseVideoBankDetailVO.getTitle(),request);
    }


    /**
     * @Description "我的课程"
     * @Date 2018/11/22 15:10
     * @Param [videoUrl]
     * @return com.bjwk.utils.DataWrapper<java.lang.Void>
     */
    @Override
    public DataWrapper<PageInfo<CourseVideoBankVO>> queryMyCourseList(String token,int currentPage,int numberPerPage) {
        DataWrapper<PageInfo<CourseVideoBankVO>> dataWrapper = new DataWrapper<PageInfo<CourseVideoBankVO>>();
        String userId = regLoginService.getUserIdByToken(token);
        PageHelper.startPage(currentPage, numberPerPage);
        List<CourseVideoBankVO> courseVideoBankVOList = courseLibraryDao.queryMyCourseList(userId);
        PageInfo<CourseVideoBankVO> page = new PageInfo<CourseVideoBankVO>(courseVideoBankVOList);
        dataWrapper.setData(page);
        return dataWrapper;
    }

    /**
     * @Description "关键字检索视屏课程库课程"
     * @Date 2018/11/22 16:48
     * @Param [token]
     * @return void
     */
    @Override
    public DataWrapper<Object> queryCourseByKeyword(String keyword, String scrollId) {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();
        JestResult result = null;
        JestClient jestClient = jestClientConfiguration.getClient();
        try {
            //首次查询或滚动时间超时,则重新查询
            if (null == scrollId) {
                System.out.println("没翻页");
                //清除滚动ID
                clearScrollIds();
                //循环构造查询条件
                SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                searchSourceBuilder.query(QueryBuilders.multiMatchQuery(keyword, "title", "lecturer").operator(Operator.AND));
                //构造查询条件,设置索引及类型
                Search search = new Search.Builder(searchSourceBuilder.toString())
                        .addIndex("course_video_bank").setParameter(Parameters.SCROLL, SCROLL_ALIVE_TIME)
                        .build();
                //第一次检索,拍下快照
                result = jestClient.execute(search);
            } else {
                System.out.println("翻页了");
                //只能向后滚动,不能向前滚动
                //直接滚动
                SearchScroll scroll = new SearchScroll.Builder(scrollId, SCROLL_ALIVE_TIME).build();
                result = jestClient.execute(scroll);

            }
            if (result != null && !result.isSucceeded()) {
                throw new RuntimeException("ESJestClient ScrollQuery Fail...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new Gson().fromJson(result.getJsonString(), Map.class);
        System.out.println(map);
        System.out.print("数据量: " + ((List) ((LinkedTreeMap) map.get("hits")).get("hits")).size());
        dataWrapper.setData(map);
        return dataWrapper;
    }

    /**
     * 清楚滚动ID
     */
    private void clearScrollIds() {
        ClearScroll clearScroll = new ClearScroll.Builder().build();
        JestClient jestClient = jestClientConfiguration.getClient();
        try {
            jestClient.execute(clearScroll);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void httpDownload(String httpUrl, HttpServletResponse response,String title,HttpServletRequest request) {
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

            response.setContentType("multipart/form-data");
            String userAgent = request.getHeader("User-Agent");
            String oraFileName = title;
            String formFileName=oraFileName;

            // 针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                formFileName = java.net.URLEncoder.encode(formFileName, "UTF-8");
            } else {
                // 非IE浏览器的处理：
                formFileName = new String(formFileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", formFileName));
            response.setContentType("video/avi;charset=utf-8");
            response.setCharacterEncoding("UTF-8");

             fs = response.getOutputStream();
            byte[] buffer = new byte[2048];
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