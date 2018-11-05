/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:QuestionnaireController.java   2018-11-04 15:00 wangsui
 */
package com.bjwk.controller.publics.questionnaire;

import com.bjwk.model.questionnaire.Questionnaire;
import com.bjwk.service.publics.questionnaire.QuestionnaireService;
import com.bjwk.utils.DataWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * @create: 2018-11-04 15:00
 * @version：1.0
 */
@Controller
@RequestMapping("api/questionnaire")
public class QuestionnaireController {

    private static final Log _logger = LogFactory.getLog(QuestionnaireController.class);

    @Autowired
    private QuestionnaireService questionnaireService;


    @RequestMapping(value = "_addQuestionnaire")
    @ResponseBody
    public DataWrapper<Boolean> addQuestionnaireInfo(@ModelAttribute(value = "questionnaire")Questionnaire questionnaire){
        _logger.info("收集问卷调查信息，数据为："+questionnaire.toString());
        return questionnaireService.insertQusetionnaireInfo(questionnaire);
    }
}
