/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:PhotoService.java   2018-12-04 17:47 wangsui
 */
package com.bjwk.service.student.photo;

import com.bjwk.model.photo.Photo;
import com.bjwk.utils.DataWrapper;

import java.util.List;

/**
 *
 * @author: wangsui
 * @create: 2018-12-04 17:47
 * @version：1.0
 */
public interface PhotoService {

    DataWrapper<Boolean> savePhoto(Photo photo,String token);

    DataWrapper<Boolean> deletePhoto(int photoId ,String token);

    DataWrapper<List<Photo>> queryPhoto(int photoListId,String token);

    DataWrapper<Boolean> updatePhoto(Photo photo,String token);
}
