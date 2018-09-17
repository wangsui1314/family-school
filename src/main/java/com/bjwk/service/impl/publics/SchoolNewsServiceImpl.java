package com.bjwk.service.impl.publics;

import com.bjwk.dao.SchoolNewsDao;
import com.bjwk.model.CourseVideoBankVO;
import com.bjwk.service.publics.SchoolNewsService;
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
 * @description: 校园新闻逻辑处理
 * @author: liqitian.
 * @create: 2018-09-17 19:35
 **/
@Service
@Slf4j
public class SchoolNewsServiceImpl implements SchoolNewsService {

    @Autowired
    private SchoolNewsDao schoolNewsDao;

    /**
     * @Description:新增校园新闻
     * @Param: [newsTitle, newsContext, newsImage]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/17
     */
    @Override
    public DataWrapper<Void> addSchoolNews(String newsTitle, String newsContext, String newsImage) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        schoolNewsDao.addSchoolNews(newsTitle, newsContext, newsImage);
        return dataWrapper;
    }

    /**
     * @Description:更新校园新闻
     * @Param: [id, newsTitle, newsContext, newsImage]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/17
     */
    @Override
    public DataWrapper<Void> updateSchoolNews(Integer id, String newsTitle, String newsContext, String newsImage) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        schoolNewsDao.updateSchoolNews(id, newsTitle, newsContext, newsImage);
        return dataWrapper;
    }

    /**
     * @Description:删除校园新闻
     * @Param: [ids]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/17
     */
    @Override
    public DataWrapper<Void> deleteSchoolNews(String ids) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        String[] idStrs = ids.split(",");
        schoolNewsDao.deleteSchoolNews(idStrs);
        return dataWrapper;
    }

    /**
     * @Description:查看校园新闻
     * @Param: [id]
     * @return: com.bjwk.utils.DataWrapper<java.lang.Void>
     * @Author: liqitian
     * @Date: 2018/9/17
     */
    @Override
    public DataWrapper<Object> querySchoolNews(String id, int currentPage, int numberPerPage) {
        DataWrapper<Object> dataWrapper = new DataWrapper<Object>();

        Jedis jedis = RedisClient.getJedis();
        /**
         * 检查id 是否为null if null newsDetail  else newsList
         */
        if (id == null) {
            //列表
            PageHelper.startPage(currentPage, numberPerPage);
            List<HashMap<String, Object>> mapList = schoolNewsDao.querySchoolNewsList();
            for (HashMap<String, Object> map : mapList) {
                String ID = map.get("id").toString();
                String newsBrowseNumber = jedis.hget("newsBrowseNumber", ID);
                map.put("newsBrowseNumber", newsBrowseNumber == null ? 0 : newsBrowseNumber);
            }
            PageInfo<HashMap<String, Object>> page = new PageInfo<HashMap<String, Object>>(mapList);
            dataWrapper.setData(page);
            jedis.close();
            return dataWrapper;
        }
        //详情

        Long browseNumber = jedis.hincrBy("newsBrowseNumber", String.valueOf(id), 1);
        jedis.close();
        HashMap<String, Object> mapDetail = schoolNewsDao.querySchoolNewsDetail(id);
        mapDetail.put("newsBrowseNumber", browseNumber);
        dataWrapper.setData(mapDetail);
        return dataWrapper;
    }
}
