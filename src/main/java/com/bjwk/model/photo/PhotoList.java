/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:PhotoList.java   2018-12-04 16:08 wangsui
 */
package com.bjwk.model.photo;

import com.bjwk.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户相册列表
 * @author: wangsui
 * @create: 2018-12-04 16:08
 * @version：1.0
 */

@Getter
@Setter
@ToString
public class PhotoList extends BaseEntity {

    private int photoListId;

    private String userId;

    private String listName;

    private String listDescribe;

    private String listImageUrl;

}
