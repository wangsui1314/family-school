/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:QuestionController.java   2018-12-17 17:41 wangsui
 */
package com.bjwk.controller.publics.questionnaire;

import com.bjwk.model.questionnaire.Question;
import com.bjwk.service.publics.questionnaire.QuestionService;
import com.bjwk.utils.DataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @author: wangsui
 * @create: 2018-12-17 17:41
 * @version：1.0
 */
@Controller
@RequestMapping("api/question")
@Slf4j
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("_find")
    @ResponseBody
    public DataWrapper<List<Question>> findQuestion(String questionId){
        log.info("问卷查询ID：{}",questionId);
        return this.questionService.queryQuestion(Integer.parseInt(questionId));
    }

    @RequestMapping("_save")
    @ResponseBody
    public DataWrapper<Boolean> saveQuestionInfo(Question question){
        log.info("添加问卷信息：{}",question.toString());
        return this.questionService.saveQuestion(question);
    }


}
