/*
 * Copyright (c)  2019
 * All rights reserved.
 *
 * Id:ProblemsService.java   2019-02-12 11:40 wangsui
 */
package com.bjwk.service.student.problems;

import com.bjwk.model.problems.Problems;

import java.util.List;

/**
 * @author: wangsui
 * @description：${description}
 * @create: 2019-02-12 11:40
 * @version：1.0
 */
public interface ProblemsService {

     int insert(Problems pojo);

     int insertList(List<Problems> pojos);

     List<Problems> select(Problems pojo) ;

     int update(Problems pojo);
}
