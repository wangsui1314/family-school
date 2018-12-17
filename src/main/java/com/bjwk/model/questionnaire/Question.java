/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:Question.java   2018-12-17 16:16 wangsui
 */
package com.bjwk.model.questionnaire;

import com.bjwk.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
 * @create: 2018-12-17 16:16
 * @version：1.0
 */
@Getter
@Setter
@ToString
public class Question extends BaseEntity {

    private int questionId;

    private String questionTitle;

    private String questionContent;

    private String otherContent;
}
