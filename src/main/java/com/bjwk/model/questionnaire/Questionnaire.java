/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:Questionnaire.java   2018-11-04 13:58 wangsui
 */
package com.bjwk.model.questionnaire;

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
public class Questionnaire implements Serializable {

    private int naireId;

    private String studentName;

    private String studentClass;

    private String parentPhone;

    private String address;

    private String selectKey;

    private String answerKey;

    private Date createTime;

    private Date updateTime;

    public int getNaireId() {
        return naireId;
    }

    public void setNaireId(int naireId) {
        this.naireId = naireId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSelectKey() {
        return selectKey;
    }

    public void setSelectKey(String selectKey) {
        this.selectKey = selectKey;
    }

    public String getAnswerKey() {
        return answerKey;
    }

    public void setAnswerKey(String answerKey) {
        this.answerKey = answerKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "naireId=" + naireId +
                ", studentName='" + studentName + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", parentPhone='" + parentPhone + '\'' +
                ", address='" + address + '\'' +
                ", selectKey='" + selectKey + '\'' +
                ", answerKey='" + answerKey + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
