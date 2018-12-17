/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:Questionnaire.java   2018-11-04 13:58 wangsui
 */
package com.bjwk.model.questionnaire;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

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
 * @create: 2018-11-04 13:58
 * @version：1.0
 */
@Getter
@Setter
@ToString
public class Questionnaire implements Serializable {

    private int naireId;

    private String studentName;

    private String studentClass;

    private String parentPhone;

    private String address;

    private String selectKey;

    private String answerKey;

    private int questionId;

    private Date createTime;

    private Date updateTime;


}
