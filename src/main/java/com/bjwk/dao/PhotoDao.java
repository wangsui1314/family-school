/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:PhotoDao.java   2018-12-04 17:33 wangsui
 */
package com.bjwk.dao;

import com.bjwk.model.photo.Photo;
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
 * @create: 2018-12-04 17:33
 * @version：1.0
 */
public interface PhotoDao {


    int savePhoto(Photo photo);

    int deletePhoto(@Param("photoId")int photoId );

    List<Photo> queryPhoto(@Param("photoListId") int photoListId);

    int updatePhoto(Photo photo);
}
