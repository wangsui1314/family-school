package com.bjwk.controller.publics;

import com.bjwk.dao.CourseLibraryDao;
import com.bjwk.dao.RegLoginDao;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.RedisClient;
import com.bjwk.utils.annotation.TokenValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

/**
 * @program: family-school
 * @description: 用户收藏接口
 * @author: liqitian.
 * @create: 2018-09-09 18:49
 **/
@Controller
@RequestMapping("api/userCollection")
public class UserCollectionController {

    @Autowired
    private CourseLibraryDao courseLibraryDao;

    @Autowired
    private RegLoginDao regLoginDao;

    @TokenValidate
    @ResponseBody
    @RequestMapping("/collection")
    public DataWrapper<Void> userCollection(
            @RequestParam("token") String token,
            @RequestParam("thingId") Integer thingId,
            @RequestParam("type") Integer type
    ) {
        DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
        Jedis jedis = RedisClient.getJedis();
        String userName = jedis.hget("loginStatus", token);
        String userId = regLoginDao.getUserIdByUserName(userName);
        //检查该"课程" 该用户是否收藏 if true collection else cancle collection
        Integer count = courseLibraryDao.queryIsCollection(userId, thingId, type);
        if (count == null){
            courseLibraryDao.insertCollection(userId, thingId, type);
        }else {
            courseLibraryDao.deleteCollection(count);
        }
        dataWrapper.setMsg("操作成功");
        return dataWrapper;
    }
}

