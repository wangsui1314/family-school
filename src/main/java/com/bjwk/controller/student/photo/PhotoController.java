/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:PhotoController.java   2018-12-04 17:52 wangsui
 */
package com.bjwk.controller.student.photo;

import com.bjwk.model.photo.Photo;
import com.bjwk.service.student.photo.PhotoService;
import com.bjwk.utils.DataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *  相片接口
 * @author: wangsui
 * @create: 2018-12-04 17:52
 * @version：1.0
 */
@Controller
@RequestMapping("api/student/photo")
@Slf4j
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "_list")
    @ResponseBody
    public DataWrapper<List<Photo>> findPhoto(@Param("photoListId") String photoListId, @Param("token")String token) {
        log.info("查询相片：接收的参数为：token ===>{}",token);
        return this.photoService.queryPhoto(Integer.parseInt(photoListId),token);
    }

    @RequestMapping(value = "_save")
    @ResponseBody
    public DataWrapper<Boolean> savePhoto(Photo photo, @Param("token") String token){
        log.info("用户相片添加 ：接收到的参数为：photo==>{},token ===>{}",photo,token);
        return this.photoService.savePhoto(photo,token);

    }

    @RequestMapping(value = "_update")
    @ResponseBody
    public DataWrapper<Boolean> updatePhoto( Photo photo, @Param("token") String token){
        log.info("用户相片修改 ：接收到的参数为：photo==>{},token ===>{}",photo,token);
        return this.photoService.updatePhoto(photo,token);
    }


    @RequestMapping(value = "_delete")
    @ResponseBody
    public DataWrapper<Boolean> deletePhoto(@Param("photoId") String photoId, @Param("token")String token){
        log.info("删除相片：接收的参数为：token ===>{},photoId ===>{}",token,photoId);
        return this.photoService.deletePhoto(Integer.parseInt(photoId), token);

    }
}
