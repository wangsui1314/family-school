/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:QuestionDao.java   2018-12-17 16:20 wangsui
 */
package com.bjwk.dao;

import com.bjwk.model.questionnaire.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.Date;
import java.util.List;

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
    @Select("select * from question where questionId = :questionId")
    Question findQuestion(@Param("questionId") int questionId);

    /**
     * 插入问卷
     **/
    @Insert({"insert into question(question_title,question_content,other_content,create_time,update_time) set (#{questionTitle},#{questionContent},#{otherContent},#{createTime, jdbcType=TIMESTAMP},#{updateTime, jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true, keyProperty = "question_id")
    int insertQuesetion(@Param("questionTitle") String questionTitle, @Param("questionContent") String questionContent, @Param("otherContent") String otherContent, @Param("createTime") Date createTime, @Param("updateTime") Date updateTime);
}
