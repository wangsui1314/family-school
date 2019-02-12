/*
 * Copyright (c)  2019
 * All rights reserved.
 *
 * Id:ProblemsVo.java   2019-02-12 15:35 wangsui
 */
package com.bjwk.controller.student.problems.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: wangsui
 * @description：${description}
 * @create: 2019-02-12 15:35
 * @version：1.0
 */

@Getter
@Setter
@ToString
public class ProblemsVo {

    private String token;

    //题目描述（单选题或多选题）
    private  String problemDetails;

    //是否单选题（0单选 1多选）
    private Integer isSingleElection;

    //题目难易度
    private  String facilityValue;

    //题目类型（常考题或非常考题）
    private String problemType;

    //年级
    private String grade;

    //科目
    private String subject;




}
