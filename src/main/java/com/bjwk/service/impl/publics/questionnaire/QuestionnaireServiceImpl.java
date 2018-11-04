/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:QuestionnaireServiceImpl.java   2018-11-04 15:01 wangsui
 */
package com.bjwk.service.impl.publics.questionnaire;

import com.bjwk.dao.QuestionnaireDao;
import com.bjwk.model.questionnaire.Questionnaire;
import com.bjwk.service.impl.student.ArticleServiceImpl;
import com.bjwk.service.publics.questionnaire.QuestionnaireService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    private static final Log _logger = LogFactory.getLog(QuestionnaireServiceImpl.class);

    @Autowired
    private QuestionnaireDao questionnaireDao;

    /**
     * 调查问卷填写结果保存
     *
     * @param questionnaire
     */
    @Override
    public DataWrapper<Boolean> insertQusetionnaireInfo(Questionnaire questionnaire) {
        _logger.info("收集问卷调查信息");
        DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();
        _logger.info("收集问卷调查信息，数据为："+questionnaire.toString());
        boolean result = questionnaireDao.insertQusetionnaireInfo(questionnaire);

        if (result) {
            dataWrapper.setMsg("填写问卷成功");
            dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
            dataWrapper.setData(result);
        } else {
            dataWrapper.setMsg("填写问卷失败");
            dataWrapper.setCallStatus(CallStatusEnum.FAILED);
            dataWrapper.setData(result);
        }
        return dataWrapper;
    }
}
