/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:QuestionServiceImpl.java   2018-12-17 17:03 wangsui
 */
package com.bjwk.service.impl.publics.questionnaire;

import com.bjwk.dao.QuestionDao;
import com.bjwk.model.questionnaire.Question;
import com.bjwk.service.publics.questionnaire.QuestionService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 *
 * @author: wangsui
 * @create: 2018-12-17 17:03
 * @version：1.0
 */
@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;


    @Override
    public DataWrapper<Question> queryQuestion(int questionId) {
        log.info("查询问卷的ID为：{}", questionId);
        Question questionList = questionDao.findQuestion(questionId);
        DataWrapper<Question> dataWrapper = new DataWrapper<Question>();
        if(StringUtils.isEmpty(questionList)){
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setData(null);
            dataWrapper.setMsg("没有找到相关问卷");
        }else {
            dataWrapper.setMsg("查询成功");
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            dataWrapper.setData(questionList);
        }
        return dataWrapper;
    }

    @Override
    public DataWrapper<Boolean> saveQuestion(Question question) {
        log.info("插入问卷信息：{}", question.toString());

        int insertNum = questionDao.insertQuestion(question);
        DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();
        if(insertNum > 0){
            dataWrapper.setData(Boolean.TRUE);
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            dataWrapper.setMsg("插入成功");
        }else{
            dataWrapper.setMsg("添加失败");
            dataWrapper.setData(Boolean.FALSE);
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
        }
        return dataWrapper;
    }
}
