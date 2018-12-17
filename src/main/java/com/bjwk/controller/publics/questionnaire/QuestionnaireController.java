/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:QuestionnaireController.java   2018-11-04 15:00 wangsui
 */
package com.bjwk.controller.publics.questionnaire;

import com.bjwk.controller.publics.questionnaire.vo.QuestionnaireVo;
import com.bjwk.model.questionnaire.Questionnaire;
import com.bjwk.service.publics.questionnaire.QuestionnaireService;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.ExportExcel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
    public DataWrapper<Boolean> addQuestionnaireInfo(@ModelAttribute(value = "questionnaire") Questionnaire questionnaire) {
        _logger.info("收集问卷调查信息，数据为：" + questionnaire.toString());
        return questionnaireService.insertQusetionnaireInfo(questionnaire);
    }

    /**
     * 导出问卷调查信息
     **/
    @RequestMapping(value = "_export")
    public void export(String questionId, HttpServletRequest request, HttpServletResponse response) {
        _logger.info("导出用户数据");
        if(StringUtils.isEmpty(questionId)){
            questionId = "1";
        }
        List<Questionnaire> questionnaireList = questionnaireService.findNaireInfo(Integer.parseInt(questionId));

        List<QuestionnaireVo> body = new ArrayList<QuestionnaireVo>();

        for (Questionnaire questionnaire : questionnaireList) {
            QuestionnaireVo respVo = new QuestionnaireVo();
            respVo.setNaireId(questionnaire.getNaireId());
            respVo.setStudentName(questionnaire.getStudentName());
            respVo.setStudentClass(questionnaire.getStudentClass());
            respVo.setParentPhone(questionnaire.getParentPhone());
            respVo.setAddress(questionnaire.getAddress());
            body.add(respVo);
        }

        ExportExcel<QuestionnaireVo> ee = new ExportExcel<QuestionnaireVo>();
        String[] headers = {"序号", "学生姓名", "学生班级", "家长手机号码", "家庭住址"};
        String fileName = "用户信息表";
        ee.exportExcel(headers, body, fileName, response);
    }
}
