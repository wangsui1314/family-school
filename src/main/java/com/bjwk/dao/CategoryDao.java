/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:CategoryDao.java   2018-11-29 16:52 wangsui
 */
package com.bjwk.dao;

import com.bjwk.model.Category;

import java.util.List;

/**
 * 美文阅读类别 DAO
 * @author: wangsui
 * @create: 2018-11-29 16:52
 * @version：1.0
 */
public interface CategoryDao {

    List<Category> findCategory();
 }
