/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:PhotoListService.java   2018-12-04 17:46 wangsui
 */
package com.bjwk.service.student.photo;

import com.bjwk.model.photo.PhotoList;
import com.bjwk.utils.DataWrapper;

import java.util.List;

/**
 *
 * @author: wangsui
 * @create: 2018-12-04 17:46
 * @version：1.0
 */
public interface PhotoListService {

    DataWrapper<Boolean> savePhotoList(PhotoList photoList,String token);

    DataWrapper<Boolean> deletePhotoList(int photoListId ,String token);

    DataWrapper<List<PhotoList>> queryPhotoList(String token);

    DataWrapper<Boolean> updatePhotoList(PhotoList photoList,String token);
}
