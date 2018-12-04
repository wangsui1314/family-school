/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:Photo.java   2018-12-04 16:05 wangsui
 */
package com.bjwk.model.photo;

import com.bjwk.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 相片存放
 * @author: wangsui
 * @create: 2018-12-04 16:05
 * @version：1.0
 */
@Getter
@Setter
@ToString
public class Photo extends BaseEntity {

    private int photoId;

    private int photoListId;

    private String photoUrl;

    private String photoName;

    private String photoDescribe;

}
