/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:PhotoServiceImpl.java   2018-12-04 17:47 wangsui
 */
package com.bjwk.service.impl.photo;

import com.bjwk.dao.PhotoDao;
import com.bjwk.dao.RegLoginDao;
import com.bjwk.model.photo.Photo;
import com.bjwk.model.photo.PhotoList;
import com.bjwk.service.student.photo.PhotoService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author: wangsui
 * @create: 2018-12-04 17:47
 * @version：1.0
 */
@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;

    @Autowired
    private RegLoginDao regLoginDao;


    @Override
    public DataWrapper<Boolean> savePhoto(Photo photo,String token) {
        DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();

        Jedis jedis = RedisClient.getJedis();
        String userName = (String) jedis.hget("loginStatus", token);
        String userId = regLoginDao.getUserIdByUserName(userName);
        if(StringUtils.isEmpty(userId)){
            dataWrapper.setData(Boolean.FALSE);
            dataWrapper.setMsg("当前用户不存在!");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            return dataWrapper;
        }

        int result = this.photoDao.savePhoto(photo);

        if(result > 0){
            dataWrapper.setData(Boolean.TRUE);
            dataWrapper.setMsg("添加相片成功");
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
        }else{
            dataWrapper.setData(Boolean.FALSE);
            dataWrapper.setMsg("添加相片失败");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Boolean> deletePhoto(int photoId,String token) {
        DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();

        Jedis jedis = RedisClient.getJedis();
        String userName = (String) jedis.hget("loginStatus", token);
        String userId = regLoginDao.getUserIdByUserName(userName);
        if(StringUtils.isEmpty(userId)){
            dataWrapper.setData(Boolean.FALSE);
            dataWrapper.setMsg("当前用户不存在!");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            return dataWrapper;
        }

        int result = this.photoDao.deletePhoto(photoId);

        if(result > 0){
            dataWrapper.setData(Boolean.TRUE);
            dataWrapper.setMsg("删除相片成功");
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
        }else{
            dataWrapper.setData(Boolean.FALSE);
            dataWrapper.setMsg("删除相片失败");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<Photo>> queryPhoto(int photoListId,String token) {

        DataWrapper<List<Photo>> dataWrapper = new DataWrapper<List<Photo>>();

        Jedis jedis = RedisClient.getJedis();
        String userName = (String) jedis.hget("loginStatus", token);
        String userId = regLoginDao.getUserIdByUserName(userName);
        if(StringUtils.isEmpty(userId)){
            dataWrapper.setData(new ArrayList<Photo>());
            dataWrapper.setMsg("当前用户不存在!");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            return dataWrapper;
        }

        List<Photo> photoList = this.photoDao.queryPhoto(photoListId);
        if(CollectionUtils.isEmpty(photoList)){
            dataWrapper.setData(new ArrayList<Photo>());
            dataWrapper.setMsg("当前相册暂未有相片");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        }else{
            dataWrapper.setData(photoList);
            dataWrapper.setMsg("查询成功");
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Boolean> updatePhoto(Photo photo,String token) {
        DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();

        Jedis jedis = RedisClient.getJedis();
        String userName = (String) jedis.hget("loginStatus", token);
        String userId = regLoginDao.getUserIdByUserName(userName);
        if(StringUtils.isEmpty(userId)){
            dataWrapper.setData(Boolean.FALSE);
            dataWrapper.setMsg("当前用户不存在!");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            return dataWrapper;
        }

        int result = this.photoDao.updatePhoto(photo);

        if(result > 0){
            dataWrapper.setData(Boolean.TRUE);
            dataWrapper.setMsg("修改相片成功");
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
        }else{
            dataWrapper.setData(Boolean.FALSE);
            dataWrapper.setMsg("修改相片失败");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        }
        return dataWrapper;
    }
}
