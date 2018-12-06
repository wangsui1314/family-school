/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:PhotoListController.java   2018-12-04 17:52 wangsui
 */
package com.bjwk.controller.student.photo;

import com.bjwk.model.photo.PhotoList;
import com.bjwk.service.student.photo.PhotoListService;
import com.bjwk.utils.DataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 相册接口
 *
 * @author: wangsui
 * @create: 2018-12-04 17:52
 * @version：1.0
 */
@Controller
@RequestMapping("api/student/photo/album")
@Slf4j
public class PhotoListController {

    @Autowired
    private PhotoListService photoListService;

    @RequestMapping(value = "_list")
    @ResponseBody
    public DataWrapper<List<PhotoList>> findPhotoAlbum(String token) {
        log.info("查询相册：接收的参数为：token ===>{}", token);
        return this.photoListService.queryPhotoList(token);
    }

    @RequestMapping(value = "_save")
    @ResponseBody
    public DataWrapper<Boolean> savePhotoList(PhotoList photoList, @Param("token") String token) {
        log.info("用户相册添加 ：接收到的参数为：photoList==>{},token ===>{}", photoList, token);
        return this.photoListService.savePhotoList(photoList, token);

    }

    @RequestMapping(value = "_update")
    @ResponseBody
    public DataWrapper<Boolean> updateAlbum(PhotoList photoList, @Param("token") String token) {
        log.info("用户相册修改 ：接收到的参数为：photoList==>{},token ===>{}", photoList, token);
        return this.photoListService.updatePhotoList(photoList, token);
    }


    @RequestMapping(value = "_delete")
    @ResponseBody
    public DataWrapper<Boolean> deleteAlbum(@Param("photoListId") String photoListId, @Param("token") String token) {
        log.info("删除相册：接收的参数为：token ===>{},photoListId ===>{}", token, photoListId);
        return this.photoListService.deletePhotoList(Integer.parseInt(photoListId), token);

    }
}
