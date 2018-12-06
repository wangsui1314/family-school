/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:PhotoListServiceImpl.java   2018-12-04 17:47 wangsui
 */
package com.bjwk.service.impl.photo;

import com.bjwk.dao.PhotoListDao;
import com.bjwk.dao.RegLoginDao;
import com.bjwk.model.photo.PhotoList;
import com.bjwk.service.student.photo.PhotoListService;
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
 * @author: wangsui
 * @create: 2018-12-04 17:47
 * @version：1.0
 */
@Slf4j
@Service
public class PhotoListServiceImpl implements PhotoListService {

    @Autowired
    private PhotoListDao photoListDao;

    @Autowired
    private RegLoginDao regLoginDao;

    @Override
    public DataWrapper<Boolean> savePhotoList(PhotoList photoList, String token) {
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
        photoList.setUserId(userId);

        int result = photoListDao.savePhotoList(photoList);
        if(result > 0){
            dataWrapper.setData(Boolean.TRUE);
            dataWrapper.setMsg("添加相册成功");
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
        }else{
            dataWrapper.setData(Boolean.FALSE);
            dataWrapper.setMsg("添加相册失败");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        }
        return dataWrapper;

    }

    @Override
    public DataWrapper<Boolean> deletePhotoList(int photoListId, String token) {

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

        int result = this.photoListDao.deletePhotoList(photoListId);

        if(result > 0){
            dataWrapper.setData(Boolean.TRUE);
            dataWrapper.setMsg("删除相册成功");
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
        }else{
            dataWrapper.setData(Boolean.FALSE);
            dataWrapper.setMsg("删除相册失败");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<List<PhotoList>> queryPhotoList(String token) {
        log.info("接收传递的token为：{}", token);
        DataWrapper<List<PhotoList>> dataWrapper = new DataWrapper<List<PhotoList>>();

        Jedis jedis = RedisClient.getJedis();
        String userName = (String) jedis.hget("loginStatus", token);
        String userId = regLoginDao.getUserIdByUserName(userName);
        if(StringUtils.isEmpty(userId)){
            dataWrapper.setData(new ArrayList<PhotoList>());
            dataWrapper.setMsg("当前用户不存在!");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            return dataWrapper;
        }

        List<PhotoList> photoListList = photoListDao.queryPhotoList(userId);
        if(CollectionUtils.isEmpty(photoListList)){
            dataWrapper.setData(new ArrayList<PhotoList>());
            dataWrapper.setMsg("当前用户还未创建相册");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        }else{
            dataWrapper.setData(photoListList);
            dataWrapper.setMsg("查询成功");
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Boolean> updatePhotoList(PhotoList photoList, String token) {

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
        photoList.setUserId(userId);
        int result = this.photoListDao.updatePhotoList(photoList);

        if(result > 0){
            dataWrapper.setData(Boolean.TRUE);
            dataWrapper.setMsg("修改相册成功");
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
        }else{
            dataWrapper.setData(Boolean.FALSE);
            dataWrapper.setMsg("修改相册失败");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
        }
        return dataWrapper;
    }

}
