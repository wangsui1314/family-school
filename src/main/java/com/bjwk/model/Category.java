/*
 * Copyright (c)  2018, XiQiao
 * All rights reserved.
 *
 * Id:Category.java   2018-11-29 16:49 wangsui
 */
package com.bjwk.model;

import java.io.Serializable;

/**
 *
 * 美文类型Modal
 * @author: wangsui
 * @create: 2018-11-29 16:49
 * @version：1.0
 */
public class Category implements Serializable {

    private int categoryId;

    private String categoryName;

    private String categoryDetails;

    private String categoryPid;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDetails() {
        return categoryDetails;
    }

    public void setCategoryDetails(String categoryDetails) {
        this.categoryDetails = categoryDetails;
    }

    public String getCategoryPid() {
        return categoryPid;
    }

    public void setCategoryPid(String categoryPid) {
        this.categoryPid = categoryPid;
    }


    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDetails='" + categoryDetails + '\'' +
                ", categoryPid='" + categoryPid + '\'' +
                '}';
    }
}
