/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:QuestionnaireDao.java   2018-11-04 15:09 wangsui
 */
package com.bjwk.dao;

import com.bjwk.model.questionnaire.Questionnaire;
import org.apache.ibatis.annotations.Param;

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
 * @create: 2018-11-04 15:09
 * @version：1.0
 */
public interface QuestionnaireDao {


    /**
     * 调查问卷填写结果保存
     **/
    int insertQusetionnaireInfo(Questionnaire questionnaire);

    /**
     * 查询问卷信息
     **/
    List<Questionnaire> findNaireInfo(@Param("questionId") int questionId);

}
