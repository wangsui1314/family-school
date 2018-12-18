/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:QuestionDao.java   2018-12-17 16:20 wangsui
 */
package com.bjwk.dao;

import com.bjwk.model.questionnaire.Question;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 *
 * @author: wangsui
 * @create: 2018-12-17 16:20
 * @version：1.0
 */
public interface QuestionDao {

    /**
     * 查询问卷
     **/
    Question findQuestion(@Param("questionId") int questionId);

    /**
     * 插入问卷
     **/
    int insertQuestion(@Param("questionTitle") String questionTitle, @Param("questionContent") String questionContent, @Param("otherContent") String otherContent, @Param("createTime") Date createTime, @Param("updateTime") Date updateTime);
}
