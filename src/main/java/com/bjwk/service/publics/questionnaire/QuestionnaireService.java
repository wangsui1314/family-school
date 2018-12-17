/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:QuestionnaireService.java   2018-11-04 15:01 wangsui
 */
package com.bjwk.service.publics.questionnaire;

import com.bjwk.model.questionnaire.Questionnaire;
import com.bjwk.utils.DataWrapper;

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
 * @create: 2018-11-04 15:01
 * @version：1.0
 */
public interface QuestionnaireService {

    /**
     * 调查问卷填写结果保存
     **/
    DataWrapper<Boolean> insertQusetionnaireInfo(Questionnaire questionnaire);

    /**
     * 查询问卷信息
     **/
    List<Questionnaire> findNaireInfo(int questionId);
}
