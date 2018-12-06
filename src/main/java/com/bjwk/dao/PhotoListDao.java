/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:PhotoListDao.java   2018-12-04 16:28 wangsui
 */
package com.bjwk.dao;

import com.bjwk.model.photo.PhotoList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2018
 * </p>
 * <p>
 * Company: XiQiao
 * </p>
 *
 * @author: wangsui
 * @create: 2018-12-04 16:28
 * @version：1.0
 */
public interface PhotoListDao {

    int savePhotoList(PhotoList photoList);

    int deletePhotoList(@Param("photoListId")int photoListId );

    List<PhotoList> queryPhotoList(@Param("userId") String userId);

    int updatePhotoList(PhotoList photoList);


}
