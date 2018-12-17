/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:QuestionService.java   2018-12-17 17:01 wangsui
 */
package com.bjwk.service.publics.questionnaire;

import com.bjwk.model.questionnaire.Question;
import com.bjwk.utils.DataWrapper;

import java.util.List;

/**
 * @author: wangsui
 * @create: 2018-12-17 17:01
 * @version：1.0
 */
public interface QuestionService {

    DataWrapper<List<Question>> queryQuestion(int questionId);

    DataWrapper<Boolean> saveQuestion(Question question);
}
