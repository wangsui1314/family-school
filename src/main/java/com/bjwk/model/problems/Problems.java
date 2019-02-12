/*
 * Copyright (c)  2019
 * All rights reserved.
 *
 * Id:Problems.java   2019-02-12 11:05 wangsui
 */
package com.bjwk.model.problems;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: wangsui
 * @description：${题库字段}
 * @create: 2019-02-12 11:05
 * @version：1.0
 */

@Getter
@Setter
@ToString
public class Problems implements Serializable {

    //题目id
    private Long id;

    //题目标题
    private String problemTitle;

    //题目内容
    private String problemContent;

    //题目描述（单选题或多选题）
    private  String problemDetails;

    //题目选项
    private String problemOption;

    //题目答案
    private String problemKey;

    //题目答案分析
    private String keyAnalysis;

    //是否单选题
    private Integer isSingleElection;

    //题目来源
    private  String problemFrom;

    //题目难易度
    private  String facilityValue;

    //题目类型（常考题或非常考题）
    private String problemType;

    //年级
    private String grade;

    //科目
    private String subject;

    /**
     * 首次插入时间
     */
    private  Date createTime;

    /**
     * 最后修改时间
     */
    private  Date updateTime;




}
