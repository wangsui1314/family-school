package com.bjwk.service.impl.student;

import com.bjwk.dao.AppConfigDao;
import com.bjwk.dao.CourseLibraryDao;
import com.bjwk.dao.RegLoginDao;
import com.bjwk.model.CourseVideoBankVO;
import com.bjwk.model.pojo.UserCollection;
import com.bjwk.service.student.CourseLibraryService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.RedisClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;

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
    private RegLoginDao regLoginDao;

    @Override
    public DataWrapper<Object> getCourseClassType() {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();
        String strConfig = appConfigDao.getCourseClassType();
        dataWrapper.setData(strConfig);
        return dataWrapper;
    }


    /**
     * @Description:查询视屏课程列表
     * @Param: [categoryId, isCharge]
     * @return: com.bjwk.utils.DataWrapper<List                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               CourseVideoBankVO>>
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
        checkCourseIsCollection(courseVideoBankVOList, token);

        PageInfo<CourseVideoBankVO> page = new PageInfo<CourseVideoBankVO>(courseVideoBankVOList);
        dataWrapper.setData(page);
        return dataWrapper;
    }

    /**
     * 检查该用户是否对课程进行收藏
     * @param courseVideoBankVOList
     * @param token
     */
    public void checkCourseIsCollection(List<CourseVideoBankVO> courseVideoBankVOList, String token) {
        if (token != null) {
            Jedis jedis = RedisClient.getJedis();
            String userName = jedis.hget("loginStatus", token);
            String userId = regLoginDao.getUserIdByUserName(userName);
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

    }
}
